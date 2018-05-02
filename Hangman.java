import java.util.Scanner;

public class Hangman
{
	
	private String answer;
	private boolean[] correct, al_geraden;
	private int numberOfTries;
	
	public static void main(String[] args)
	{	
		Hangman hm = new Hangman("Grasmaaier", 5);
		hm.play();
	}
	
	public Hangman()
	{
		this("Testwoord", 10);
	}
	
	public Hangman(String answer, int numtries)
	{
		this.answer = answer.toLowerCase();
		this.correct = new boolean[answer.length()];
		this.numberOfTries = numtries;
		this.al_geraden = new boolean[26];
	}
	
	public void display()
	{
		for(int i = 0; i < this.correct.length; ++i)
		{
			if(this.correct[i])
				System.out.print(answer.charAt(i));
			else
				System.out.print("_");
		}
		System.out.println();
	}
	
	public void displayStatus()
	{
		for(int i = 0; i < 26; ++i)
		{
			if(al_geraden[i])
				System.out.print((char)(i+97));
			else
				System.out.print("_");
		}
		System.out.println("\nJe kunt nog " + this.numberOfTries + " keer raden!");
		
	}
	
	public boolean evalString(String ip)
	{
		if(ip.equals("stop"))
		{
			System.out.println("Afgebroken");
			return true;
		}	
		else if(ip.equalsIgnoreCase(this.answer))
		{
			System.out.println("YOU WIN");
			return true;
		}
		else
		{
			System.out.println("Slechte input!");
			return false;
		}
		
	}
	
	public boolean evalChar(char c)
	{
		if(c == '?')
		{
			displayStatus();
			return true;
		}
		if(c < 97 || c > 122)
		{
			System.out.println("Slechte input!");
			return true;
		}
		if(this.al_geraden[c-97])
		{
			System.out.println("Letter werd al geraden!");
			return true;
		}
		if(this.answer.indexOf(c)==-1)
		{
			--this.numberOfTries;
			this.al_geraden[c-97] = true;
			display();
			return true;
		}
		
		return false;
		
		
	}
	
	public void play()
	{
		System.out.println("=========================================");
		System.out.println("		HANGMAN ");
		System.out.println("'?' om de status weer te geven");
		System.out.println("'stop' om af te breken");
		System.out.println("=========================================");
		System.out.println("Het gezochte woord heeft " + this.answer.length() + " Letters!");
		
		char guess;
		int count = 0;
		boolean check;
		String ip;
		
		Scanner sc = new Scanner(System.in);
		
		while(numberOfTries>0)
		{
			System.out.print("Raad een letter (of het woord):");
			
			ip = sc.nextLine();
			
			if(ip.length()!=1)
			{
				if(evalString(ip))
				{
					return;
				}
				else
				{
					continue;
				}
			}
			
			if(evalChar(guess = ip.toLowerCase().charAt(0)))
			{
				continue;
			}
			
			al_geraden[guess-97] = true;
			
			for(int i = 0; i < answer.length(); ++i)
			{
				if(guess==this.answer.toLowerCase().charAt(i))
				{
					this.correct[i] = true;
					++count;
					
					if(count == answer.length())
					{	
						display();
						System.out.println("YOU WIN");
						return;
					}				
				}
			}	
			
			display();
		}	
		System.out.println("Out of guesses, YOU LOSE");	
	}
	
		
}