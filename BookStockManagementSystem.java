import java.io.*;
import java.lang.*;
import java.util.Scanner;

class mylib
{
	private InputStreamReader ir=new InputStreamReader(System.in);
	private BufferedReader br;
	private FileOutputStream fos;
	private	PrintStream ps; 
	private FileReader frs;
	
	
	private boolean checkFile(String filename)
	{
		File myFile=new File(filename);
		boolean b=myFile.exists();	
		
		return b;
	}
	
	
	private String generate_BkID()
	{
		boolean bflag=checkFile("book.txt");
		String bk_id="";
		String thisLine;
		int cnt=0;
		
		if(bflag==false)
			bk_id="0001";
		else
		{
			try
			{
				FileReader frs1=new FileReader("book.txt");
			 	BufferedReader br1=new BufferedReader(frs1);
				while((thisLine=br1.readLine()) != null)
				{
					if(!thisLine.equals(""))
						cnt++;					
				}
				frs1.close();
				br1.close();
				
				if(cnt>0)
					cnt++;
				
				if(cnt<=9)
					bk_id="000" + cnt;
				else
					bk_id="00" + cnt;
				
				
			}
			catch(Exception ex)
			{
				System.out.println("Could not generate the Item ID...!");
			}
			
		}
		
		return bk_id;
	}
	
	private void addItem()
	{
		String bk_tit,auth,shop,edi;
		String id;
		double cost=0;
		int stock=0,rol=0,nob=0;
		
		try
 		{
			
			id=generate_BkID();
			
			System.out.print("\n Book ID :"+id+"\n");
			br=new BufferedReader(ir);
			
			System.out.print("\n Purchased From :");
			shop=br.readLine();
			
			System.out.print("\n Book Title :");
			bk_tit=br.readLine();
			
			System.out.print("\n Author :");
			auth=br.readLine();
			
			System.out.print("\n Edition :");
			edi=br.readLine();
			
			System.out.print("\n Price :");
			cost=Double.parseDouble(br.readLine());
			
			System.out.print("\n No. of Books :");
			nob=Integer.parseInt(br.readLine());		
			
			fos=new FileOutputStream("book.txt",true);
			ps=new PrintStream(fos);
			
			ps.println(id+","+shop+","+bk_tit+","+ auth+"," + edi + "," +cost+","+nob);
			fos.close();
			System.out.println("Successfully added...");
			
		}
 		catch(Exception ex)
 		{
 			System.out.println("Error in accepting Book specifications...");
 		}	
	}
	
	private void makeTransaction()
	{
		String id,bk_tit,s="y";
		String msg,stock;
		String value[]=new String[5];
		double cost=0,amount=0;
		int qty=0;
	}
	
	private void display_book()
	{
			String thisLine;
			try
			{
				frs=new FileReader("book.txt");
			 	br=new BufferedReader(frs);
				System.out.println("-------------------------------------------------------------------------------------------------------------------");
				System.out.println("| Book ID | \tPublisher\t | \t Book Title \t | \t Author	\t | Edition |  Price  | No of Books |");
				System.out.println("-------------------------------------------------------------------------------------------------------------------");
				while((thisLine=br.readLine()) != null)
				  {
					String[] str=thisLine.split(",");					
					System.out.print("| ");
					for(int i=0;i<str.length;i++)
					{
						if(i==1 )
						{
							if(str[i].length()<=5)
								System.out.print( str[i] + "      \t \t | ");
							else
								System.out.print( str[i] + "\t | ");
						}	
						else if(i==2 )
						{							
							if(str[i].length()<=5)
								System.out.print( str[i] + "      \t \t | ");
							else
								System.out.print( str[i] + "\t | ");
						}
						else if(i==3)
						{							
							if(str[i].length()<=5)
								System.out.print( str[i] + " \t\t | ");
							else
								System.out.print( str[i] + "\t | ");
						}
						else if(i==4)
							System.out.print( str[i] + "     | ");
						else if(i==5)
							System.out.print( str[i] + "   | ");
						else if(i==6)
							System.out.print( str[i] + "     | ");
						else
							System.out.print( str[i] + " | ");
					}
					System.out.println();
				}
				frs.close();
				br.close();
			}
			catch(Exception ex)
			{
				System.out.println("No Books Found");
			}
	}
	
	private void trans_book() 
	{
        boolean sflag = false;
        Scanner in = null;
		String result="";
		
		StringBuilder sb=new StringBuilder();
		
        try 
		{
			InputStreamReader r2=new InputStreamReader(System.in);  
			BufferedReader br2=new BufferedReader(r2);  
  
			System.out.println("Enter Book ID :");  
			String bk_id=br2.readLine();  
			
            in = new Scanner(new FileReader("book.txt"));
            while(in.hasNextLine()) 
			{
                result = in.nextLine();
				if(result.contains(bk_id))
				{
					String[] str=result.split(",");
					
				System.out.println("No of Books Avaiable :"  + str[6]);  
				System.out.println("Enter No of Books Issued From Stock:");  
				int nob_gn=Integer.parseInt(br2.readLine());
				int nob_av=Integer.parseInt(str[6]);
				if(nob gn>nob_av)
				System.out.println("No Sufficient Books Available");
				else
					{
						int tnob=nob_av-nob_gn;
						str[6]="" + tnob;
						       result=str[0]+","+str[1]+","+str[2]+","+str[3]+","+str[4]+","+str[5]+","+str[6;
					}
					
					sflag=true;
					
				}
				sb.append(result);
				sb.append("\n");
            }
			System.out.println("---------------------------------");
			if(sflag==true)			
			{
				in.close();
				File fl=new File("book.txt");
				fl.delete();
				fos=new FileOutputStream("book.txt",true);
				ps=new PrintStream(fos);
				
				ps.println(sb);
				fos.close();
				System.out.println("Successfully Modified...");
			}
			else
				System.out.println("BOOK NOT FOUND");
			System.out.println("---------------------------------");
        }
        catch(IOException e) {
            e.printStackTrace();      
        }
        finally {
            try { in.close() ; } catch(Exception e) { /* ignore */ }  
        }
        
    }
	
	private void search_book(String book_id) 
	{
        boolean sflag = false;
        Scanner in = null;
		String result="";
		
		
        try {
            in = new Scanner(new FileReader("book.txt"));
            while(in.hasNextLine()) 
			{
                result = in.nextLine();
				if(result.contains(book_id))
				{				
					sflag=true;
					break;
				}
            }
			System.out.println("---------------------------------");
			if(sflag==true)			
			{	
				String[] str=result.split(",");
				System.out.println("Book ID   	  	:" + str[0]);
				System.out.println("Publisher 	  	:" + str[1]);
				System.out.println("Book Title 	  	:" + str[2]);
				System.out.println("Author 		  	:" + str[3]);
				System.out.println("Edition 	  	:" + str[4]);
				System.out.println("Price 		  	:" + str[5]);
				System.out.println("No of Books Available 	:" + str[6]);
			}
			else
				System.out.println("BOOK NOT FOUND");
		System.out.println("---------------------------------");
        }
        catch(IOException e) {
           e.printStackTrace();      
        }
        finally {
            try { in.close() ; } catch(Exception e) { /* ignore */ }  
        }
        
    }
	
	public static void main(String args[]) throws IOException
	{
		mylib inv=new mylib();
		int ch=0;
		String s;
		InputStreamReader ir=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(ir);
		
		System.out.println("--------------------------------------------------------------------");
		System.out.println(" BOOK    STOCK    MANAGEMENT   SYSTEM  ");
		System.out.println("--------------------------------------------------------------------");
		
		begin:
		while(ch<4)
		{
			System.out.println("");
			System.out.println("---------------------------------");
			System.out.println("1. ADD NEW BOOK");
			System.out.println("2. VIEW BOOK DETAILS");
			System.out.println("3. STOCK ISSUE");
			System.out.println("---------------------------------");
			System.out.println("4. Exit");
		
			System.out.print("\n");
			System.out.print("\n Enter yours choice :");
			ch=Integer.parseInt(br.readLine());
			
			if(ch>3||ch<1)
			{
				System.exit(1);
				continue begin;	
			}
			
			if(ch==1)
			{
				s="y";
				while(s.equals("y")||s.equals("Y"))
				{
					inv.addItem();
					System.out.print("\n Add another[y/n]:");
					s=br.readLine();						
				}
				continue begin;
			}
			else if(ch==2)
			{
				inv.display_book();
			}
			else if(ch==3)
			{
				inv.trans_book();		
			}	
		
		}
	}
}
