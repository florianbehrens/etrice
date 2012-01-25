#include "CommunicationProtocol.h"

/*--------------------- port classes */



/*--------------------- debug helpers */

/* TODO: make this optional or different for smaller footprint */
/* message names as strings for debugging (generate MSC) */
static const char* CommunicationProtocol_messageStrings[] = {"MIN", "receivedData","sendData", "sendData2", "MAX"};

const char* CommunicationProtocol_getMessageString(int msg_id) {
	if (msg_id<CommunicationProtocol_MSG_MIN || msg_id>CommunicationProtocol_MSG_MAX+1){
		/* id out of range */
		return "Message ID out of range";
	}
	else{
		return CommunicationProtocol_messageStrings[msg_id];
	}
}