/*******************************************************************************
 * Copyright (c) 2011 protos software gmbh (http://www.protos.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * CONTRIBUTORS:
 * 		Thomas Schuetz (initial contribution)
 *
 *******************************************************************************/

#include "etMessageQueue.h"

#include "etMSCLogger.h"

void etMessageQueue_init(etMessageQueue* self){
	ET_MSC_LOGGER_SYNC_ENTRY("etMessageQueue", "init")
	self->first = NULL;
	self->last = NULL;
	self->highWaterMark = 0;
	self->size = 0;
	ET_MSC_LOGGER_SYNC_EXIT
}


void etMessageQueue_push(etMessageQueue* self, etMessage* msg){
	// TODO: optimize queue for concurrent push / pop
	ET_MSC_LOGGER_SYNC_ENTRY("etMessageQueue", "push")
	if (self->first == NULL) {
		/*no message in queue*/
		self->first = self->last = msg;
	}
	else {
		/*at least one message in queue*/
		self->last->next = msg;
		self->last = msg;
	}
	msg->next = NULL; /*TODO: optimization: this line could be removed if we assume that all messages are initialized*/

	if (++self->size > self->highWaterMark)
		self->highWaterMark++;

	ET_MSC_LOGGER_SYNC_EXIT
}

etMessage* etMessageQueue_pop(etMessageQueue* self){
	ET_MSC_LOGGER_SYNC_ENTRY("etMessageQueue", "pop")
	etMessage* pop_msg = self->first;
	if(self->first == NULL){
		/*no message in queue*/
		ET_MSC_LOGGER_SYNC_EXIT
		return NULL;
	}
	if (self->first->next==NULL){
		/*only one message in queue*/
		self->first = self->last = NULL;
	}
	else {
		/*more than one message in queue -> set first to nex message*/
		self->first = self->first->next;
	}

	pop_msg->next=NULL;
	self->size--;

	ET_MSC_LOGGER_SYNC_EXIT
	return pop_msg;
}

etInt16 etMessageQueue_getSize(etMessageQueue* self) {
	ET_MSC_LOGGER_SYNC_ENTRY("etMessageQueue", "init")
	ET_MSC_LOGGER_SYNC_EXIT
	return self->size;
}

etMessage* etMessageQueue_getFirst(etMessageQueue* self){
	ET_MSC_LOGGER_SYNC_ENTRY("etMessageQueue", "init")
	ET_MSC_LOGGER_SYNC_EXIT
	return self->first;
}

etMessage* etMessageQueue_getLast(etMessageQueue* self){
	ET_MSC_LOGGER_SYNC_ENTRY("etMessageQueue", "init")
	ET_MSC_LOGGER_SYNC_EXIT
	return self->last;
}

etBool etMessageQueue_isNotEmpty(etMessageQueue* self){
	ET_MSC_LOGGER_SYNC_ENTRY("etMessageQueue", "init")
	ET_MSC_LOGGER_SYNC_EXIT
	return self->last != NULL;
}

etInt16 etMessageQueue_getHightWaterMark(etMessageQueue* self) {
	ET_MSC_LOGGER_SYNC_ENTRY("etMessageQueue", "init")
	ET_MSC_LOGGER_SYNC_EXIT
	return self->highWaterMark;
}
