/*******************************************************************************
 * Copyright (c) 2011 protos software gmbh (http://www.protos.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * CONTRIBUTORS:
 * 		Henrik Rentz-Reichert (initial contribution)
 * 
 *******************************************************************************/

package org.eclipse.etrice.generator.java.gen

import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.JavaIoFileSystemAccess
import com.google.inject.Inject

import static extension org.eclipse.xtext.xtend2.lib.ResourceExtensions.*
import static extension org.eclipse.xtext.xtend2.lib.*

import org.eclipse.etrice.core.room.*
import org.eclipse.etrice.generator.etricegen.*
import org.eclipse.etrice.generator.java.setup.Logger

class ProtocolClassGen {

	@Inject extension JavaIoFileSystemAccess fileAccess
	@Inject extension StdExtensions stdExt
	@Inject extension ProcedureHelpers helpers
	@Inject Logger logger
	
	def doGenerate(Root root) {
		for (pc: root.usedProtocolClasses) {
			var path = pc.generationTargetPath+pc.getPath
			var file = pc.getFileName
			logger.logInfo("generating ProtocolClass implementation '"+file+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(file, root.generate(pc))
		}
	}
	
	def generate(Root root, ProtocolClass pc) {'''
		package �pc.getPackage()�;
		
		import java.util.ArrayList;
		
		import org.eclipse.etrice.runtime.java.messaging.Address;
		import org.eclipse.etrice.runtime.java.messaging.Message;
		import org.eclipse.etrice.runtime.java.modelbase.*;
		import org.eclipse.etrice.runtime.java.debugging.DebuggingService;
		
		�helpers.UserCode(pc.userCode1)�
		
		�var models = root.getReferencedModels(pc)�
		�FOR model : models�import �model.name�.*;
		�ENDFOR�
		
		public class �pc.name� {
			// message IDs
			// TODO: separate class for message IDs: class MSG{public static volatile int MSG_MIN = 0; ...} -> better structure
			// error if msgID <= MSG_MIN
			public static final int MSG_MIN = 0;   
			//IDs for outgoing messages
			�FOR message : pc.getAllOutgoingMessages()�
				public static final int OUT_�message.name� = �pc.getAllOutgoingMessages().indexOf(message)+1�;
			�ENDFOR�
			//IDs for incoming messages
			�FOR message : pc.getAllIncomingMessages()�
				public static final int IN_�message.name� = �pc.getAllIncomingMessages().indexOf(message)+pc.getAllOutgoingMessages().size+1�;
			�ENDFOR�
			//error if msgID >= MSG_MAX
			public static final int MSG_MAX = �pc.getAllOutgoingMessages().size + pc.getAllIncomingMessages().size+1�;  
		
			�helpers.UserCode(pc.userCode2)�
		
			private static String messageStrings[] = {"MIN", �FOR m : pc.getAllOutgoingMessages()�"�m.name�",�ENDFOR� �FOR m : pc.getAllIncomingMessages()�"�m.name�",�ENDFOR�"MAX"};
		
			public String getMessageString(int msg_id) {
				if (msg_id<0 || msg_id>MSG_MAX+1){
					// id out of range
					return "Message ID out of range";
				}
				else{
					return messageStrings[msg_id];
				}
			}
		
			�portClass(pc, false)�
			�portClass(pc, true)�
		}
	'''
	}
	
	def portClass(ProtocolClass pc, Boolean conj) {'''
		�var name = pc.getPortClassName(conj)�
		�var pclass = pc.getPortClass(conj)�
		
		// port class
		static public class �name� extends PortBase {
			�IF pclass!=null�
				�helpers.UserCode(pclass.userCode)�
			�ENDIF�
			// constructors
			public �name�(IEventReceiver actor, String name, int localId, Address addr, Address peerAddress) {
				super(actor, name, localId, 0, addr, peerAddress);
				DebuggingService.getInstance().addPortInstance(this);
			}
			public �name�(IEventReceiver actor, String name, int localId, int idx, Address addr, Address peerAddress) {
				super(actor, name, localId, idx, addr, peerAddress);
				DebuggingService.getInstance().addPortInstance(this);
			}
		
			@Override
			public void receive(Message m) {
					if (!(m instanceof EventMessage))
						return;
					EventMessage msg = (EventMessage) m;
					if (msg.getEvtId() <= 0 || msg.getEvtId() >= MSG_MAX)
						System.out.println("unknown");
					else {
						if (messageStrings[msg.getEvtId()] != "timerTick"){
							// TODOTS: model switch for activation
							DebuggingService.getInstance().addMessageAsyncIn(getPeerAddress(), getAddress(), messageStrings[msg.getEvtId()]);
						}
						�IF pc.handlesReceive(conj)�
						switch (msg.getEvtId()) {
							�FOR hdlr : pc.getReceiveHandlers(conj)�
								case �hdlr.msg.getCodeName()�:
								{
									�FOR command : hdlr.detailCode.commands�
										�command�
									�ENDFOR�
								}
								break;
							�ENDFOR�
							default:
						�ENDIF�
							if (msg instanceof EventWithDataMessage)
								getActor().receiveEvent(this, msg.getEvtId(), ((EventWithDataMessage)msg).getData());
							else
								getActor().receiveEvent(this, msg.getEvtId(), null);
						�IF pc.handlesReceive(conj)�
						}
						�ENDIF�
					}
			}
		
			�IF pclass!=null�
				�helpers.Attributes(pclass.attributes)�
				�helpers.Operations(pclass.operations)�
			�ENDIF�
			
			// sent messages
			�FOR m : pc.getOutgoing(conj)�
				�sendMessage(m, conj)�
			�ENDFOR�
		}
		
		// replicated port class
		static public class �name�Repl {
			private ArrayList<�name�> ports;
			private int replication;
		
			public �name�Repl(IEventReceiver actor, String name, int localId, Address[] addr,
					Address[] peerAddress) {
				replication = addr.length;
				ports = new ArrayList<�pc.name�.�name�>(replication);
				for (int i=0; i<replication; ++i) {
					ports.add(new �name�(
							actor, name+i, localId, i, addr[i], peerAddress[i]));
				}
			}
			
			public int getReplication() {
				return replication;
			}
			
			public int getIndexOf(InterfaceItemBase ifitem){
					return ifitem.getIdx();
				}
			
			public �name� get(int i) {
				return ports.get(i);
			}
			
			�IF conj�
			// incoming messages
			�FOR m : pc.getAllIncomingMessages()�
			�messageSignature(m)�{
				for (int i=0; i<replication; ++i) {
					ports.get(i).�messageCall(m)�;
				}
			}
			�ENDFOR�
			�ELSE�
			// outgoing messages
			�FOR m : pc.getAllOutgoingMessages()�
			�messageSignature(m)�{
				for (int i=0; i<replication; ++i) {
					ports.get(i).�messageCall(m)�;
				}
			}
			�ENDFOR�
			�ENDIF�
		}
		
		// interface for port class
		public interface I�name�{
			�IF conj�
			// outgoing messages
			�FOR m : pc.getAllOutgoingMessages()� �messageSignature(m)�;
			�ENDFOR�
			�ELSE�
			// incoming messages
			�FOR m : pc.getAllIncomingMessages()� �messageSignature(m)�;
			�ENDFOR�
			�ENDIF�
		}
	'''
	}

	def messageSignature(Message m) {'''
	public void �m.name� (�IF m.data!=null��m.data.type.typeName()� �m.data.name��ENDIF�)
	'''
	}

	def messageCall(Message m) {'''
	�m.name�(�IF m.data!=null� �m.data.name��ENDIF�)
	'''}
	
	def sendMessage(Message m, boolean conj) {'''
	�var dir = if (conj) "IN" else "OUT"�
	�var hdlr = m.getSendHandler(conj)�
	�messageSignature(m)�{
		�IF hdlr!=null�
			�FOR command : hdlr.detailCode.commands�	�command�
			�ENDFOR�
		�ELSE�
			if (messageStrings[ �dir�_�m.name�] != "timerTick"){
				// TODOTS: model switch for activation
			DebuggingService.getInstance().addMessageAsyncOut(getAddress(), getPeerAddress(), messageStrings[�dir�_�m.name�]);
			}
			if (getPeerAddress()!=null)
			�IF m.data==null�getPeerMsgReceiver().receive(new EventMessage(getPeerAddress(), �dir�_�m.name�));
			�ELSE�getPeerMsgReceiver().receive(new EventWithDataMessage(getPeerAddress(), �dir�_�m.name�, �m.data.name��IF (!m.data.type.ref && m.data.type.type!=null)�.deepCopy()�ENDIF�));
			�ENDIF�
		�ENDIF�
	}
	'''
	}
}