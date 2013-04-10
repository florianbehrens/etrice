/**
 * @author generated by eTrice
 *
 * Header File of ActorClass Join
 * 
 */

#ifndef _JOIN_H_
#define _JOIN_H_

#include "etDatatypes.h"
#include "messaging/etMessage.h"

#include "CommunicationProtocol.h"
#include "SecurityProtocol.h"


typedef struct Join Join;

/* const part of ActorClass (ROM) */
typedef struct Join_const {
	/* simple ports */
	const CommunicationProtocolConjPort outport;
	const SecurityProtocolConjPort security;
	
	/* data receive ports */

	/* saps */
	
	/* replicated ports */
	const etReplPort inport;
	
	/* services */
} Join_const;


/* constant for state machine data */
#define JOIN_HISTORY_SIZE 2

/* variable part of ActorClass (RAM) */
struct Join {
	const Join_const* const constData;
	
	/* data send ports */

	/*--------------------- attributes ---------------------*/
	int32 counter;
	
	
		/* state machine variables */
		etInt16 state;
		etInt16 history[JOIN_HISTORY_SIZE];
};

void Join_init(Join* self);

void Join_receiveMessage(void* self, void* ifitem, const etMessage* msg);


/*--------------------- operations ---------------------*/


#endif /* _JOIN_H_ */

