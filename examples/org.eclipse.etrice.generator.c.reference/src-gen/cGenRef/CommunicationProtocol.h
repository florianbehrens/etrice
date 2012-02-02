/**
 * @author generated by eTrice
 *
 * Header File of ProtocolClass CommunicationProtocol
 * 
 */

#ifndef _COMMUNICATIONPROTOCOL_H_
#define _COMMUNICATIONPROTOCOL_H_

#include "etDatatypes.h"
#include "etPort.h"


#include "DataClass1.h"

/* message IDs */
enum {
	CommunicationProtocol_MSG_MIN = 0, 
	/* IDs for outgoing messages */
	CommunicationProtocol_OUT_receivedData = 1,
	/* IDs for incoming messages */
	CommunicationProtocol_IN_sendData = 2,
	CommunicationProtocol_IN_sendData2 = 3,
	/* error if msgID >= MSG_MAX */
	CommunicationProtocol_MSG_MAX = 4
};

/*--------------------- port classes */

typedef etPort CommunicationProtocolPort;

void CommunicationProtocolPort_receivedData(const CommunicationProtocolPort* self);





typedef etPort CommunicationProtocolConjPort;

void CommunicationProtocolConjPort_sendData(const CommunicationProtocolConjPort* self);
void CommunicationProtocolConjPort_sendData2(const CommunicationProtocolConjPort* self);





/*--------------------- debug helpers */

/* get message string for message id */
const char* CommunicationProtocol_getMessageString(int msg_id);



#endif /* _COMMUNICATIONPROTOCOL_H_ */

