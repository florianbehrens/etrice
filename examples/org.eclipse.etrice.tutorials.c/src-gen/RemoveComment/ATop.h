/**
 * @author generated by eTrice
 *
 * Header File of ActorClass ATop
 * 
 */

#ifndef _ATOP_H_
#define _ATOP_H_

#include "etDatatypes.h"
#include "messaging/etMessage.h"



typedef struct ATop ATop;

/* const part of ActorClass (ROM) */
/* this actor class has no ports and thus no constant data */


/* variable part of ActorClass (RAM) */
struct ATop {
	/* data send ports */

	/*--------------------- attributes ---------------------*/
	
};

void ATop_init(ATop* self);

void ATop_receiveMessage(void* self, void* ifitem, const etMessage* msg);


/*--------------------- operations ---------------------*/


#endif /* _ATOP_H_ */

