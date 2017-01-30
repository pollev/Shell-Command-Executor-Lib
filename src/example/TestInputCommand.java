package example;

import java.io.IOException;

import shellcommand.Command;
import shellcommand.CommandExecutor;
import shellcommand.NonMatchingOSException;
import shellcommand.ProcessNotYetStartedException;
import shellcommand.CommandExecutor.OS;

public class TestInputCommand extends Command{

	@Override
	public String getCommand() {
		return "cat";
	}

	@Override
	public OS getOSType() {
		return OS.UNIX;
	}
	
	public static void main(String[] args) {

		CommandExecutor ex = CommandExecutor.getCommandExecutor();
		Command command = new TestInputCommand();
		try {
			ex.executeCommand(command);
			System.out.println("command: " + command.getCommand());
			System.out.println("status: " + command.getStatus() + "\n\n");
			
			command.writeToProcessStdIn("hello world");
			
			//command.waitForCompletion();
			
			System.out.println("status: " + command.getStatus() + "\n\n");

			if(command.waitForOutput()){
				System.out.println("output: " + command.getNormalOutput());
			}else{
				System.out.println("error: " + command.getErrorOutput());				
			}
			
			System.out.println("part 2");
			command.writeToProcessStdIn("second input");
			if(command.waitForOutput()){
				System.out.println("output: " + command.getNormalOutput());
			}else{
				System.out.println("error: " + command.getErrorOutput());				
			}
			
			System.out.println("\n \n end output");

		} catch (NonMatchingOSException | IOException | ProcessNotYetStartedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
