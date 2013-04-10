/**
 * @author generated by eTrice
 *
 * Instance File of Node node1 with SubSystem sys1
 * - instantiation of all actor instances and port instances
 * - configuration of data and connection of ports
 */

#include "messaging/etMessageService.h"
#include "platform/etMemory.h"

/* include all used ActorClasses */
#include "Join.h"
#include "Redirect.h"
#include "Tester.h"
#include "Supervisor.h"
#include "Fork.h"

/* include all used ProtcolClasses */
#include "SecurityProtocol.h"
#include "CommunicationProtocol.h"

/* instantiation of message services and message buffers */
/* PhysicalThread1 */
static uint8 msgBuffer_PhysicalThread1[50 * 64]; /* msgBuffer_<threadname>[<msgpoolsize> * <msgblocksize>] */ 
static etMessageService msgService_PhysicalThread1;
/* PhysicalThread2 */
static uint8 msgBuffer_PhysicalThread2[50 * 64]; /* msgBuffer_<threadname>[<msgpoolsize> * <msgblocksize>] */ 
static etMessageService msgService_PhysicalThread2;
/* PhysicalThread3 */
static uint8 msgBuffer_PhysicalThread3[50 * 64]; /* msgBuffer_<threadname>[<msgpoolsize> * <msgblocksize>] */ 
static etMessageService msgService_PhysicalThread3;

/* declarations of all ActorClass instances (const and variable structs) */

/* forward declaration of variable actor structs */
static Tester _LS_sys1_tester;
static Fork _LS_sys1_fork;
static Redirect _LS_sys1_redirect1;
static Redirect _LS_sys1_redirect2;
static Redirect _LS_sys1_redirect3;
static Redirect _LS_sys1_redirect4;
static Redirect _LS_sys1_redirect5;
static Redirect _LS_sys1_redirect6;
static Redirect _LS_sys1_redirect7;
static Redirect _LS_sys1_redirect8;
static Redirect _LS_sys1_redirect9;
static Redirect _LS_sys1_redirect10;
static Join _LS_sys1_join;
static Supervisor _LS_sys1_supervisor;

/* forward declaration of variable port structs */		


/* instance _LS_sys1_tester */
static const Tester_const _LS_sys1_tester_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread2, 115, 1} /* Port outport */,
	{0, &msgService_PhysicalThread2, 157, 2} /* Port inport */
	
	/* data receive ports */
};
static Tester _LS_sys1_tester = {
	&_LS_sys1_tester_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_fork */
static const etReplSubPort _LS_sys1_fork_repl_sub_ports[10] = {
	/* Replicated Sub Ports: {varData, msgService, peerAddress, localId, index} */
	{0,&msgService_PhysicalThread2, 117, 1, 0}, /* Repl Sub Port broadcast idx +0*/
	{0,&msgService_PhysicalThread2, 120, 1, 1}, /* Repl Sub Port broadcast idx +1*/
	{0,&msgService_PhysicalThread1, 123, 1, 2}, /* Repl Sub Port broadcast idx +2*/
	{0,&msgService_PhysicalThread1, 126, 1, 3}, /* Repl Sub Port broadcast idx +3*/
	{0,&msgService_PhysicalThread1, 129, 1, 4}, /* Repl Sub Port broadcast idx +4*/
	{0,&msgService_PhysicalThread1, 132, 1, 5}, /* Repl Sub Port broadcast idx +5*/
	{0,&msgService_PhysicalThread1, 135, 1, 6}, /* Repl Sub Port broadcast idx +6*/
	{0,&msgService_PhysicalThread1, 138, 1, 7}, /* Repl Sub Port broadcast idx +7*/
	{0,&msgService_PhysicalThread1, 141, 1, 8}, /* Repl Sub Port broadcast idx +8*/
	{0,&msgService_PhysicalThread1, 144, 1, 9} /* Repl Sub Port broadcast idx +9*/
};
static const Fork_const _LS_sys1_fork_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread1, 102, 2} /* Port inport */,
	{10, _LS_sys1_fork_repl_sub_ports+0}
	
	/* data receive ports */
};
static Fork _LS_sys1_fork = {
	&_LS_sys1_fork_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_redirect1 */
static const Redirect_const _LS_sys1_redirect1_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread2, 105, 1} /* Port inport */,
	{0, &msgService_PhysicalThread2, 147, 2} /* Port outport */
	
	/* data receive ports */
};
static Redirect _LS_sys1_redirect1 = {
	&_LS_sys1_redirect1_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_redirect2 */
static const Redirect_const _LS_sys1_redirect2_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread2, 106, 1} /* Port inport */,
	{0, &msgService_PhysicalThread2, 148, 2} /* Port outport */
	
	/* data receive ports */
};
static Redirect _LS_sys1_redirect2 = {
	&_LS_sys1_redirect2_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_redirect3 */
static const Redirect_const _LS_sys1_redirect3_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread2, 107, 1} /* Port inport */,
	{0, &msgService_PhysicalThread2, 149, 2} /* Port outport */
	
	/* data receive ports */
};
static Redirect _LS_sys1_redirect3 = {
	&_LS_sys1_redirect3_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_redirect4 */
static const Redirect_const _LS_sys1_redirect4_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread2, 108, 1} /* Port inport */,
	{0, &msgService_PhysicalThread2, 150, 2} /* Port outport */
	
	/* data receive ports */
};
static Redirect _LS_sys1_redirect4 = {
	&_LS_sys1_redirect4_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_redirect5 */
static const Redirect_const _LS_sys1_redirect5_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread2, 109, 1} /* Port inport */,
	{0, &msgService_PhysicalThread2, 151, 2} /* Port outport */
	
	/* data receive ports */
};
static Redirect _LS_sys1_redirect5 = {
	&_LS_sys1_redirect5_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_redirect6 */
static const Redirect_const _LS_sys1_redirect6_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread2, 110, 1} /* Port inport */,
	{0, &msgService_PhysicalThread2, 152, 2} /* Port outport */
	
	/* data receive ports */
};
static Redirect _LS_sys1_redirect6 = {
	&_LS_sys1_redirect6_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_redirect7 */
static const Redirect_const _LS_sys1_redirect7_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread2, 111, 1} /* Port inport */,
	{0, &msgService_PhysicalThread2, 153, 2} /* Port outport */
	
	/* data receive ports */
};
static Redirect _LS_sys1_redirect7 = {
	&_LS_sys1_redirect7_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_redirect8 */
static const Redirect_const _LS_sys1_redirect8_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread2, 112, 1} /* Port inport */,
	{0, &msgService_PhysicalThread2, 154, 2} /* Port outport */
	
	/* data receive ports */
};
static Redirect _LS_sys1_redirect8 = {
	&_LS_sys1_redirect8_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_redirect9 */
static const Redirect_const _LS_sys1_redirect9_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread2, 113, 1} /* Port inport */,
	{0, &msgService_PhysicalThread2, 155, 2} /* Port outport */
	
	/* data receive ports */
};
static Redirect _LS_sys1_redirect9 = {
	&_LS_sys1_redirect9_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_redirect10 */
static const Redirect_const _LS_sys1_redirect10_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread2, 114, 1} /* Port inport */,
	{0, &msgService_PhysicalThread2, 156, 2} /* Port outport */
	
	/* data receive ports */
};
static Redirect _LS_sys1_redirect10 = {
	&_LS_sys1_redirect10_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_join */
static const etReplSubPort _LS_sys1_join_repl_sub_ports[10] = {
	/* Replicated Sub Ports: {varData, msgService, peerAddress, localId, index} */
	{0,&msgService_PhysicalThread2, 118, 1, 0}, /* Repl Sub Port inport idx +0*/
	{0,&msgService_PhysicalThread2, 121, 1, 1}, /* Repl Sub Port inport idx +1*/
	{0,&msgService_PhysicalThread1, 124, 1, 2}, /* Repl Sub Port inport idx +2*/
	{0,&msgService_PhysicalThread1, 127, 1, 3}, /* Repl Sub Port inport idx +3*/
	{0,&msgService_PhysicalThread1, 130, 1, 4}, /* Repl Sub Port inport idx +4*/
	{0,&msgService_PhysicalThread1, 133, 1, 5}, /* Repl Sub Port inport idx +5*/
	{0,&msgService_PhysicalThread1, 136, 1, 6}, /* Repl Sub Port inport idx +6*/
	{0,&msgService_PhysicalThread1, 139, 1, 7}, /* Repl Sub Port inport idx +7*/
	{0,&msgService_PhysicalThread1, 142, 1, 8}, /* Repl Sub Port inport idx +8*/
	{0,&msgService_PhysicalThread1, 145, 1, 9} /* Repl Sub Port inport idx +9*/
};
static const Join_const _LS_sys1_join_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread1, 103, 2} /* Port outport */,
	{0, &msgService_PhysicalThread3, 160, 3} /* Port security */,
	{10, _LS_sys1_join_repl_sub_ports+0}
	
	/* data receive ports */
};
static Join _LS_sys1_join = {
	&_LS_sys1_join_const,
	
	/* data send ports */
	
	/* attributes */
	0		/* counter */
	
	/* state and history are initialized in init fuction */
};

/* instance _LS_sys1_supervisor */
static const Supervisor_const _LS_sys1_supervisor_const = {
	/* Ports: {varData, msgService, peerAddress, localId} */
	{0, &msgService_PhysicalThread2, 158, 1} /* Port application */
	
	/* data receive ports */
};
static Supervisor _LS_sys1_supervisor = {
	&_LS_sys1_supervisor_const,
	
	/* data send ports */
	
	/* attributes */
	
	/* state and history are initialized in init fuction */
};

