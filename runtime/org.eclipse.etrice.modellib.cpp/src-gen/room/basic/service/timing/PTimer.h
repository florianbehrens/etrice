/**
 * @author generated by eTrice
 *
 * Header File of ProtocolClass PTimer
 *
 */

#ifndef _ROOM_BASIC_SERVICE_TIMING_PTIMER_H_
#define _ROOM_BASIC_SERVICE_TIMING_PTIMER_H_

#include "common/modelbase/InterfaceItemBase.h"
#include "common/modelbase/PortBase.h"
#include "common/modelbase/ReplicatedInterfaceItemBase.h"
#include "common/modelbase/ReplicatedPortBase.h"
#include "common/etDatatypesCpp.hpp"


/*--------------------- begin user code ---------------------*/
#define ET_TIMER_RUNNING	0x01
#define ET_TIMER_PERIODIC	0x02
/*--------------------- end user code ---------------------*/


class PTimer {
   public:
		/* message IDs */
		typedef enum {
			MSG_MIN = 0,
			OUT_timeout = 1,
			IN_startTimer = 2,
			IN_startTimeout = 3,
			IN_kill = 4,
			MSG_MAX = 5
		} msg_ids;

		/*--------------------- begin user code ---------------------*/
		//uc2		
		/*--------------------- end user code ---------------------*/

		static bool isValidEvtID(int evtId) {
			return ((MSG_MIN < evtId) && (evtId < MSG_MAX));
		}
		static bool isValidOutgoingEvtID(int evtId) {
			return ((MSG_MIN < evtId) && (evtId < IN_startTimer));
		}
		static bool isValidIncomingEvtID(int evtId) {
			return ((IN_startTimer <= evtId) && (evtId < MSG_MAX));
		}
		static const etRuntime::String& getMessageString(int msg_id);

	private:
		static const etRuntime::String s_messageStrings[];

};

//------------------------------------------------------------------------------------------------------------
// port class
//------------------------------------------------------------------------------------------------------------
class PTimerPort : public etRuntime::PortBase {
   public:
	 PTimerPort(etRuntime::IInterfaceItemOwner* actor, const etRuntime::String& name, int localId);
	 PTimerPort(etRuntime::IInterfaceItemOwner* actor, const etRuntime::String& name, int localId, int idx);

	virtual void destroy();

	 virtual void receive(const etRuntime::Message* m);


	 // sent messages
	public: void timeout();
	private: void timeout_impl();
};

//------------------------------------------------------------------------------------------------------------
// replicated port class
//------------------------------------------------------------------------------------------------------------
class PTimerReplPort : public etRuntime::ReplicatedPortBase {

	public:
		PTimerReplPort(etRuntime::IInterfaceItemOwner* actor, const etRuntime::String& name, int localId);

		int getReplication() const { return getNInterfaceItems(); }
		int getIndexOf(const etRuntime::InterfaceItemBase& ifitem) const { return ifitem.getIdx(); }
		PTimerPort& get(int idx) const { return *dynamic_cast<PTimerPort*>(getInterfaceItem(idx)); }

		// outgoing messages
		public: void timeout();

	protected:
		virtual etRuntime::InterfaceItemBase* createInterfaceItem(etRuntime::IInterfaceItemOwner* rcv, const etRuntime::String& name, int lid, int idx) {
			return new PTimerPort(rcv, name, lid, idx);
		}

};
//------------------------------------------------------------------------------------------------------------
// conjugated port class
//------------------------------------------------------------------------------------------------------------
class PTimerConjPort : public etRuntime::PortBase {
   public:
	 PTimerConjPort(etRuntime::IInterfaceItemOwner* actor, const etRuntime::String& name, int localId);
	 PTimerConjPort(etRuntime::IInterfaceItemOwner* actor, const etRuntime::String& name, int localId, int idx);

	virtual void destroy();

	 virtual void receive(const etRuntime::Message* m);

	/*--------------------- attributes ---------------------*/
	 int8 status;
	/*--------------------- operations ---------------------*/

	 // sent messages
	public: void startTimer(uint32 time);
	private: void startTimer_impl(uint32 time);
	public: void startTimeout(uint32 time);
	private: void startTimeout_impl(uint32 time);
	public: void kill();
	private: void kill_impl();
};

//------------------------------------------------------------------------------------------------------------
// conjugated replicated port class
//------------------------------------------------------------------------------------------------------------
class PTimerConjReplPort : public etRuntime::ReplicatedPortBase {

	public:
		PTimerConjReplPort(etRuntime::IInterfaceItemOwner* actor, const etRuntime::String& name, int localId);

		int getReplication() const { return getNInterfaceItems(); }
		int getIndexOf(const etRuntime::InterfaceItemBase& ifitem) const { return ifitem.getIdx(); }
		PTimerConjPort& get(int idx) const { return *dynamic_cast<PTimerConjPort*>(getInterfaceItem(idx)); }

		// incoming messages
		public: void startTimer(uint32 time);
		public: void startTimeout(uint32 time);
		public: void kill();

	protected:
		virtual etRuntime::InterfaceItemBase* createInterfaceItem(etRuntime::IInterfaceItemOwner* rcv, const etRuntime::String& name, int lid, int idx) {
			return new PTimerConjPort(rcv, name, lid, idx);
		}

};


#endif /* _ROOM_BASIC_SERVICE_TIMING_PTIMER_H_ */
