/**
 * @author generated by eTrice
 *
 * Source File of ActorClass AFileReader
 * 
 */

#include "AFileReader.h"

#include "modelbase/etActor.h"
#include "debugging/etLogger.h"
#include "debugging/etMSCLogger.h"
#include "etUnit/etUnit.h"
#include "platform/etMemory.h"

#include "FileReaderProtocol.h"

/*--------------------- begin user code ---------------------*/
#include <stdio.h>
/*--------------------- end user code ---------------------*/

/* interface item IDs */
enum interface_items {
	IFITEM_outPort = 1
};


/* state IDs */
enum state_ids {
	NO_STATE = 0,
	STATE_TOP = 1,
	STATE_reading = 2
};

/* transition chains */
enum chain_ids {
	CHAIN_TRANS_INITIAL_TO__reading = 1,
	CHAIN_TRANS_tr0_FROM_reading_TO_reading_BY_getNextCharoutPort_tr0 = 2
};

/* triggers */
enum triggers {
	POLLING = 0,
	TRIG_outPort__getNextChar = IFITEM_outPort + EVT_SHIFT*FileReaderProtocol_IN_getNextChar
};


static void setState(AFileReader* self, int new_state) {
	self->state = new_state;
}

/* Entry and Exit Codes */

/* Action Codes */
static void action_TRANS_INITIAL_TO__reading(AFileReader* self) {
	if ((self->f /* ORIG: f */ = fopen("test.txt","r")) != 0) {
		printf("file open ok !\r\n");
		}
		else {
		printf("file not found !\r\n");
		SubSysClass_shutdown();
		}
}
static void action_TRANS_tr0_FROM_reading_TO_reading_BY_getNextCharoutPort_tr0(AFileReader* self, InterfaceItemBase ifitem) {
	int8 c;
	if ((c=fgetc(self->f /* ORIG: f */)) != EOF) {
		FileReaderProtocolPort_nextChar(&self->constData->outPort, c) /* ORIG: outPort.nextChar(c) */;
		}
		else {
		fclose(self->f /* ORIG: f */);
		printf("file closed !\r\n");
		SubSysClass_shutdown();
		}
}

/**
 * calls exit codes while exiting from the current state to one of its
 * parent states while remembering the history
 * @param current - the current state
 * @param to - the final parent state
 * @param handler - entry and exit codes are called only if not handler (for handler TransitionPoints)
 */
static void exitTo(AFileReader* self, int current, int to, boolean handler) {
	while (current!=to) {
		switch (current) {
			case STATE_reading:
				self->history[STATE_TOP] = STATE_reading;
				current = STATE_TOP;
				break;
		}
	}
}

/**
 * calls action, entry and exit codes along a transition chain. The generic data are cast to typed data
 * matching the trigger of this chain. The ID of the final state is returned
 * @param chain - the chain ID
 * @param generic_data - the generic data pointer
 * @return the ID of the final state
 */
static int executeTransitionChain(AFileReader* self, int chain, InterfaceItemBase ifitem, void* generic_data) {
	switch (chain) {
		case CHAIN_TRANS_INITIAL_TO__reading:
		{
			action_TRANS_INITIAL_TO__reading(self);
			return STATE_reading;
		}
		case CHAIN_TRANS_tr0_FROM_reading_TO_reading_BY_getNextCharoutPort_tr0:
		{
			action_TRANS_tr0_FROM_reading_TO_reading_BY_getNextCharoutPort_tr0(self, ifitem);
			return STATE_reading;
		}
	}
	return NO_STATE;
}

/**
 * calls entry codes while entering a state's history. The ID of the final leaf state is returned
 * @param state - the state which is entered
 * @param handler - entry code is executed if not handler
 * @return - the ID of the final leaf state
 */
static int enterHistory(AFileReader* self, int state, boolean handler, boolean skip_entry) {
	while (TRUE) {
		switch (state) {
			case STATE_reading:
				// in leaf state: return state id
				return STATE_reading;
			case STATE_TOP:
				state = self->history[STATE_TOP];
				break;
		}
		skip_entry = FALSE;
	}
	//return NO_STATE; // required by CDT but detected as unreachable by JDT because of while (true)
}

static void executeInitTransition(AFileReader* self) {
	int chain = CHAIN_TRANS_INITIAL_TO__reading;
	int next = executeTransitionChain(self, chain, NULL, NULL);
	next = enterHistory(self, next, FALSE, FALSE);
	setState(self, next);
}

/* receiveEvent contains the main implementation of the FSM */
static void receiveEvent(AFileReader* self, InterfaceItemBase ifitem, int evt, void* generic_data) {
	int trigger = ifitem->localId + EVT_SHIFT*evt;
	int chain = NOT_CAUGHT;
	int catching_state = NO_STATE;
	boolean is_handler = FALSE;
	boolean skip_entry = FALSE;
	
	if (!handleSystemEvent(ifitem, evt, generic_data)) {
		switch (self->state) {
			case STATE_reading:
				switch(trigger) {
					case TRIG_outPort__getNextChar:
						{
							chain = CHAIN_TRANS_tr0_FROM_reading_TO_reading_BY_getNextCharoutPort_tr0;
							catching_state = STATE_TOP;
						}
					break;
				}
				break;
		}
	}
	if (chain != NOT_CAUGHT) {
		exitTo(self, self->state, catching_state, is_handler);
		int next = executeTransitionChain(self, chain, ifitem, generic_data);
		next = enterHistory(self, next, is_handler, skip_entry);
		setState(self, next);
	}
}
	 
//******************************************
// END of generated code for FSM
//******************************************

void AFileReader_init(AFileReader* self){
	ET_MSC_LOGGER_SYNC_ENTRY("AFileReader", "init")
	self->state = STATE_TOP;
	{
		int i;
		for (i=0; i<AFILEREADER_HISTORY_SIZE; ++i)
			self->history[i] = NO_STATE;
	}
	executeInitTransition(self);
	ET_MSC_LOGGER_SYNC_EXIT
}


void AFileReader_receiveMessage(void* self, void* ifitem, const etMessage* msg){
	ET_MSC_LOGGER_SYNC_ENTRY("AFileReader", "_receiveMessage")
	
	receiveEvent(self, (etPort*)ifitem, msg->evtID, (void*)(((char*)msg)+MEM_CEIL(sizeof(etMessage))));
	
	ET_MSC_LOGGER_SYNC_EXIT
}


/*--------------------- operations ---------------------*/

