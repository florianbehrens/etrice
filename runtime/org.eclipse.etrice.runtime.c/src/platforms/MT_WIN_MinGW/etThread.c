/*******************************************************************************
 * Copyright (c) 2013 protos software gmbh (http://www.protos.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * CONTRIBUTORS:
 * 		Thomas Schuetz, Thomas Jung (initial contribution)
 *
 *******************************************************************************/

/**
 *
 * etThread.c MinGW implementation of etThread
 *
 */

#include "osal/etThread.h"

#include "debugging/etLogger.h"
#include "debugging/etMSCLogger.h"

void etThread_execute(etThread* self);

void etThread_construct(etThread* self){
	ET_MSC_LOGGER_SYNC_ENTRY("etThread", "construct")
	self->osData = (HANDLE)_beginthread( (etThreadFunction)etThread_execute, self->stacksize, self );
	SetThreadPriority(self->osData, self->priority);
	ET_MSC_LOGGER_SYNC_EXIT
}

void etThread_execute(etThread* self){
	ET_MSC_LOGGER_SYNC_ENTRY("etThread", "execute")
	/* etThread_execute redirects the call from the thread to the execute function in the eTrice runtime to enable correct synchronous MSC logging */
	self->threadFunction(self->threadFunctionData);
	ET_MSC_LOGGER_SYNC_EXIT
}

void etThread_destruct(etThread* self){
	ET_MSC_LOGGER_SYNC_ENTRY("etThread", "destruct")
	TerminateThread(self->osData, 0);
	ET_MSC_LOGGER_SYNC_EXIT
}

void etThread_sleep(etInt32 millis){
	ET_MSC_LOGGER_SYNC_ENTRY("etThread", "sleep")
	Sleep(millis);
	ET_MSC_LOGGER_SYNC_EXIT
}

etOSThreadData etThread_self(void){
	return GetCurrentThread();
}

etOSThreadId etThread_self_id(void){
	return GetCurrentThreadId();
}

