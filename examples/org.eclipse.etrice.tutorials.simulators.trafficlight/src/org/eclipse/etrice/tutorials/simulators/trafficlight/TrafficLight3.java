/*******************************************************************************
 * Copyright (c) 2012 tieto deutschland gmbh (http://www.tieto.com)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thomas Jung (initial contribution)
 *    Thomas Schuetz
 *******************************************************************************/

package org.eclipse.etrice.tutorials.simulators.trafficlight;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class TrafficLight3 extends Canvas {
	private static final long serialVersionUID = 1L;

	private int singleLightSize = 20;
	private int xPosition = 5;
	private int gap = 5;

	public OneLight redLight = new OneLight(OneLight.RED, xPosition, 0
			* singleLightSize + gap, singleLightSize, singleLightSize);
	public OneLight yellowLight = new OneLight(OneLight.YELLOW, xPosition, 1
			* singleLightSize + gap, singleLightSize, singleLightSize);
	public OneLight greenLight = new OneLight(OneLight.GREEN, xPosition, 2
			* singleLightSize + gap, singleLightSize, singleLightSize);

	public void paint(Graphics g) {

		// set Background
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, getSize().width, getSize().height);

		// draw boarder
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);

		redLight.draw(g);
		yellowLight.draw(g);
		greenLight.draw(g);

	}

	public Dimension getPreferredSize() {
		return new Dimension(singleLightSize + 2 * gap, singleLightSize * 3 + 2
				* gap);
	}

	public Dimension getMinimumSize() {
		return new Dimension(singleLightSize + 2 * gap, singleLightSize * 3 + 2
				* gap);
	}

	public void setRedLight(int state){
		redLight.setState(state);
	}
	public void setYellowLight(int state){
		yellowLight.setState(state);
	}
	public void setGreenLight(int state){
		greenLight.setState(state);
	}
}
