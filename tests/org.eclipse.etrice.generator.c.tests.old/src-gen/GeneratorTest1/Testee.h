/**
 * @author generated by eTrice
 *
 * Header File of ActorClass Testee
 * 
 */

#ifndef _TESTEE_H_
#define _TESTEE_H_

#include "etDatatypes.h"
#include "messaging/etMessage.h"

#include "PTestTrigger.h"


typedef struct Testee Testee;

/* const part of ActorClass (ROM) */
typedef struct Testee_const {
	/* simple ports */
	const PTestTriggerPort tester;
	
	/* data receive ports */

	/* saps */
	
	/* replicated ports */
	
	/* services */
} Testee_const;


/* constant for state machine data */
#define TESTEE_HISTORY_SIZE 3

/* variable part of ActorClass (RAM) */
struct Testee {
	const Testee_const* const constData;
	
	/* data send ports */

	/*--------------------- attributes ---------------------*/
	
	
		/* state machine variables */
		etInt16 state;
		etInt16 history[TESTEE_HISTORY_SIZE];
};

void Testee_init(Testee* self);

void Testee_receiveMessage(void* self, void* ifitem, const etMessage* msg);

void Testee_execute(Testee* self);

/*--------------------- operations ---------------------*/


#endif /* _TESTEE_H_ */

