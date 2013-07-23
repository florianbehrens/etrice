	/**
	 * @author generated by eTrice
	 *
	 * Source File of ProtocolClass PTimer
	 * 
	 */

	#include "PTimer.h"
	#include "common/debugging/DebuggingService.h"
	#include <iostream>

	using namespace etRuntime;
	
	
	/*--------------------- begin user code ---------------------*/
	//uc2		
	/*--------------------- end user code ---------------------*/

	//------------------------------------------------------------------------------------------------------------
	// port class
	//------------------------------------------------------------------------------------------------------------
	
	PTimerPort::PTimerPort(etRuntime::IEventReceiver& actor, etRuntime::IRTObject* parent, std::string name, int localId, Address addr, Address peerAddress, bool doRegistration)
		: PortBase(actor, parent, name, localId, 0, addr, peerAddress)
	{
		if (doRegistration) {
			DebuggingService::getInstance().addPortInstance(*this);
		}
	}
	
	PTimerPort::PTimerPort(etRuntime::IEventReceiver& actor, etRuntime::IRTObject* parent, std::string name, int localId, int idx, Address addr, Address peerAddress, bool doRegistration)
		: PortBase(actor, parent, name, localId, idx, addr, peerAddress)
	{
		if (doRegistration) {
			DebuggingService::getInstance().addPortInstance(*this);
		}
	}
		
	void PTimerPort::receive(Message* msg) {
		if (! PTimer::isValidIncomingEvtID(msg->getEvtId())) {
			std::cout << "unknown" << std::endl;
		}
		else {
			if (msg->hasDebugFlagSet()) {			// TODO: model switch for activation of this flag
				DebuggingService::getInstance().addMessageAsyncIn(getPeerAddress(), getAddress(), PTimer::getMessageString(msg->getEvtId()));
			}
			
					getEventReceiver().receiveEvent(this, msg->getEvtId(),	msg->getData());
		}
	};
	
			
	// sent messages
	void PTimerPort::timeout() {
		DebuggingService::getInstance().addMessageAsyncOut(getAddress(), getPeerAddress(),
														   PTimer::getMessageString(PTimer::OUT_timeout));
		if (getPeerAddress().isValid()){
			getPeerMsgReceiver()->receive(new Message(getPeerAddress(), PTimer::OUT_timeout));
				}
	}
	
		
	//------------------------------------------------------------------------------------------------------------
	// replicated port class
	//------------------------------------------------------------------------------------------------------------
	PTimerReplPort::PTimerReplPort(etRuntime::IEventReceiver& actor, etRuntime::IRTObject* parent, std::string name, int localId, std::vector<Address> addr, std::vector<Address> peerAddress) 
		: m_replication(addr.size()),
	  	  m_ports()
	{
		char numstr[10]; // enough to hold all numbers up to 32-bits
	
		m_ports = reinterpret_cast<PTimerPort*> (new char[sizeof(PTimerPort) * addr.size()]);
		for (int i = 0; i < m_replication; ++i) {
			snprintf(numstr, sizeof(numstr), "%d", i);
			//placement new to avoid copy construction, therefore no vector is used
			new  (&m_ports[i]) PTimerPort(actor, parent, name + numstr, localId, i, addr[i], peerAddress[i]);
		}
	};
	
		
	// outgoing messages
	void PTimerReplPort::timeout(){
		for (int i=0; i<m_replication; ++i) {
			m_ports[i].timeout();
		}
	}
	//------------------------------------------------------------------------------------------------------------
	// conjugated port class
	//------------------------------------------------------------------------------------------------------------
	
	PTimerConjPort::PTimerConjPort(etRuntime::IEventReceiver& actor, etRuntime::IRTObject* parent, std::string name, int localId, Address addr, Address peerAddress, bool doRegistration)
		: PortBase(actor, parent, name, localId, 0, addr, peerAddress),
		status(0)
	{
		// initialize attributes
		if (doRegistration) {
			DebuggingService::getInstance().addPortInstance(*this);
		}
	}
	
	PTimerConjPort::PTimerConjPort(etRuntime::IEventReceiver& actor, etRuntime::IRTObject* parent, std::string name, int localId, int idx, Address addr, Address peerAddress, bool doRegistration)
		: PortBase(actor, parent, name, localId, idx, addr, peerAddress),
		status(0)
	{
		// initialize attributes
		if (doRegistration) {
			DebuggingService::getInstance().addPortInstance(*this);
		}
	}
		
	void PTimerConjPort::receive(Message* msg) {
		if (! PTimer::isValidOutgoingEvtID(msg->getEvtId())) {
			std::cout << "unknown" << std::endl;
		}
		else {
			if (msg->hasDebugFlagSet()) {			// TODO: model switch for activation of this flag
				DebuggingService::getInstance().addMessageAsyncIn(getPeerAddress(), getAddress(), PTimer::getMessageString(msg->getEvtId()));
			}
			
			switch (msg->getEvtId()) {
				case PTimer::OUT_timeout:
					{
						
									//TODO: clear active bit in case of single shot timer
									if (status!=0){
										if (status==ET_TIMER_RUNNING){
											// single shot timer
											status=0;
										}
										// msg to fsm
										getEventReceiver().receiveEvent(this, msg->getEvtId(),	msg->getData());
									}
					}
					break;
				default:
					getEventReceiver().receiveEvent(this, msg->getEvtId(),	msg->getData());
					break;
			}
		}
	};
	
	/*--------------------- operations ---------------------*/
			
	// sent messages
	void PTimerConjPort::startTimer(uint32 time) {
			
					if (status==0){
						status=ET_TIMER_RUNNING | ET_TIMER_PERIODIC;
						DebuggingService::getInstance().addMessageAsyncOut(getAddress(), getPeerAddress(),
														   PTimer::getMessageString(PTimer::IN_startTimer));
						if (getPeerAddress().isValid()){
							getPeerMsgReceiver()->receive(new Message(getPeerAddress(),PTimer::IN_startTimer, 
							                                                reinterpret_cast<void*>(time),
							                                                sizeof(uint32)));
						}
					}
	}
	
	void PTimerConjPort::startTimeout(uint32 time) {
			
					if (status==0){
						status = ET_TIMER_RUNNING;
						DebuggingService::getInstance().addMessageAsyncOut(getAddress(), getPeerAddress(),
														   PTimer::getMessageString(PTimer::IN_startTimeout));
						if (getPeerAddress().isValid()){
							getPeerMsgReceiver()->receive(new Message(getPeerAddress(),PTimer::IN_startTimeout, 
							                                                reinterpret_cast<void*>(time),
							                                                sizeof(uint32)));
						}
					}
	}
	
	void PTimerConjPort::kill() {
			
					if (status!=0){
						status=0;
						DebuggingService::getInstance().addMessageAsyncOut(getAddress(), getPeerAddress(),
														   PTimer::getMessageString(PTimer::IN_kill));
						if (getPeerAddress().isValid()){
							getPeerMsgReceiver()->receive(new Message(getPeerAddress(),PTimer::IN_kill, 0, 0));
						}
					}
	}
	
		
	//------------------------------------------------------------------------------------------------------------
	// conjugated replicated port class
	//------------------------------------------------------------------------------------------------------------
	PTimerConjReplPort::PTimerConjReplPort(etRuntime::IEventReceiver& actor, etRuntime::IRTObject* parent, std::string name, int localId, std::vector<Address> addr, std::vector<Address> peerAddress) 
		: m_replication(addr.size()),
	  	  m_ports()
	{
		char numstr[10]; // enough to hold all numbers up to 32-bits
	
		m_ports = reinterpret_cast<PTimerConjPort*> (new char[sizeof(PTimerConjPort) * addr.size()]);
		for (int i = 0; i < m_replication; ++i) {
			snprintf(numstr, sizeof(numstr), "%d", i);
			//placement new to avoid copy construction, therefore no vector is used
			new  (&m_ports[i]) PTimerConjPort(actor, parent, name + numstr, localId, i, addr[i], peerAddress[i]);
		}
	};
	
		
	// outgoing messages
	void PTimerConjReplPort::startTimer(uint32 time){
		for (int i=0; i<m_replication; ++i) {
			m_ports[i].startTimer( time);
		}
	}
	void PTimerConjReplPort::startTimeout(uint32 time){
		for (int i=0; i<m_replication; ++i) {
			m_ports[i].startTimeout( time);
		}
	}
	void PTimerConjReplPort::kill(){
		for (int i=0; i<m_replication; ++i) {
			m_ports[i].kill();
		}
	}
	
	/*--------------------- debug helpers */
	
	/* message names as strings for debugging (generate MSC) */
	std::string PTimer::s_messageStrings[] 
			= {"MIN", 
			   "timeout",
			   "startTimer",
			   "startTimeout",
			   "kill",
			   "MAX"};
	
	std::string PTimer::getMessageString(int msg_id) {
		if ((MSG_MIN < msg_id ) && ( msg_id < MSG_MAX )) {
			return s_messageStrings[msg_id];
		} else {
			// id out of range
			return "Message ID out of range";
		}
	}
	
	
	
