/**
 * @author generated by eTrice
 *
 * this class contains the main function running component SendingData_SubSystem
 * it instantiates SendingData_SubSystem and starts and ends the lifecycle
 */

package SendingData;

import org.eclipse.etrice.runtime.java.modelbase.SubSystemRunnerBase;

class SendingData_SubSystemRunner extends SubSystemRunnerBase {

	/**
     * main function
     * creates the main component and starts and stops the lifecycle
     */
	public static void main(String[] args) {
		// instantiate the main component
		SendingData_SubSystem main_component = new SendingData_SubSystem("SendingData_SubSystem");
		
		run(main_component, args);
	}
};
