package reversi;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;

public class Reversi extends JPanel implements MouseListener, KeyListener{

	private int [][]griglia=new int[8][8];;
	private Rectangle [][]quadrati=new Rectangle[8][8];;
	private boolean fatto=false;
	private int player=2;
	private int other=1;
	protected boolean thinking = false;
	private String settings;
	
	private Handler handler;
	
	//quadrati=boxes
	//griglia=pieces
	Reversi(String ia) {
		
		
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		settings=ia;
		for(int i=0; i<quadrati.length; i++) {
			for(int j=0; j<quadrati[0].length; j++) {
				quadrati[i][j]=new Rectangle(100+i*75,100+j*75,75,75);
			}
			
		}
		for(int i=0; i<griglia.length; i++) {
			for(int j=0; j<griglia[0].length; j++) {
				griglia[i][j]=0;				
			}
			
		}
		griglia[3][3]=1;
		griglia[4][4]=1;
		griglia[3][4]=2;
		griglia[4][3]=2;
		celleValide(player);
		
		
	}
	
	
	public void paint(Graphics g) {
		requestFocus();
		System.out.println("repaint iniziato");
		int contB=0, contN=0;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 800, 800);
		g.setColor(Color.GREEN);
		g.fillRect(100, 100, 600, 600);
		g.setColor(Color.BLACK);
		
		Graphics2D g2 = (Graphics2D)g;
		for(int i=0; i<quadrati.length; i++) {
			for(int j=0; j<quadrati[0].length; j++) {
				g2.draw(quadrati[i][j]);
			}
			
		}
		
		for(int i=0; i<griglia.length; i++) {
			for(int j=0; j<griglia[0].length; j++) {
				if(griglia[i][j]>0) {
					if(griglia[i][j]==1) {
						g.setColor(Color.WHITE);
						contB++;
					}
					if(griglia[i][j]==2) {
						g.setColor(Color.BLACK);
						contN++;
					}
					if(griglia[i][j]==3) {
						g.setColor(Color.YELLOW);						
					}
					if(g.getColor()==Color.YELLOW) {
						Rectangle quadrato=quadrati[i][j];
						g.fillOval(quadrato.x+15, quadrato.y+15, quadrato.width-30, quadrato.height-30);
						
					}
					else {
						Rectangle quadrato=quadrati[i][j];
						g.fillOval(quadrato.x+5, quadrato.y+5, quadrato.width-10, quadrato.height-10);
					}
					
				}
						
			}
				
		}
		Font current=g.getFont();
		Font newFont=current.deriveFont(current.getSize()*3.0f);
		g.setFont(newFont);
		g.setColor(Color.BLACK);
		g.drawString("Neri: "+contN, 100, 60);
		g.setColor(Color.WHITE);
		g.drawString("Bianchi: "+contB, 550, 60);
		if(partitaFinita()==0) {
			g.drawString("Premi R resettare", 250, 750);
		if(player!=other) {
			g.setColor(Color.BLACK);
			g.drawString("Tocca al Nero", 250, 60);
		}
		else {
			g.drawString("Tocca al Bianco", 250, 60);
			}
		}
		else {
			if(partitaFinita()==1) {
				g.drawString("Vince il Bianco", 250, 60);
			}
			else {
				if(partitaFinita()==2) {
					g.drawString("Vince il Nero", 250, 60);
				}
				else {
					if(partitaFinita()==3){
						if(contB>contN) g.drawString("Vince il Bianco", 250, 60);
						if(contN>contB) g.drawString("Vince il Nero", 250, 60);
						if(contB==contN) g.drawString("Pareggio", 250, 60);
						removeMouseListener(this);
						g.drawString("Premi R per fare una nuova partita", 125, 750);
					}
				}
			}
			
		}
		
		if (thinking) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(300, 370, 200, 60);
			g.setColor(Color.RED);
			g.drawString("Thinking", 330, 410);
		}
		
		System.out.println("Lo score dei neri è "+contN);
		System.out.println("Lo score dei bianchi è "+contB);
			
	}
	
	
	
	int partitaFinita() {
		boolean isB=false;
		boolean isN=false;
		boolean isFull=true;
		
		for(int i=0; i<8; i++) {
				for(int j=0; j<8; j++) {
					if(griglia[i][j]==2) {
							isN=true;
					}
					if(griglia[i][j]==1) {
						isB=true;
						
					}
					if(griglia[i][j]==0 || griglia[i][j]==3) {
						isFull=false;
					}
					
				}
			
		}
		if(!isN) return 1;
		if(!isB) return 2;
		if(isFull) return 3;
		return 0;
	}
	
	//pulisce le celle gialle e le ripiazza dopo una mossa
	boolean celleValide(int a) {
		for(int i=0; i<griglia.length; i++) {
				for(int j=0; j<griglia[0].length; j++) {
					if(griglia[i][j]==3)
						griglia[i][j]=0;
				}
			
		}
		
		int b=1;
		if(a==1) b=2;
		boolean flag=false;
		
		for (int i=0; i<griglia.length; i++){
	        for (int j=0; j<griglia[0].length; j++){ //Scorre tutta la mappa
	            if (griglia[i][j] == a){
	                for (int k=i-1; k<=i+1; k++){
	                    for (int l=j-1; l<=j+1; l++){ //Scorre intorno alla cella
	                    		if(k>-1 && l>-1 && k<8 && l<8) {
	                            if (griglia[k][l] == b){
	                                if (k==i && l!=0 && l!=7){
	                                    if (l==j-1){
	                                        int x = l;
	                                        while (x>0 && griglia[k][x] == b){x--;}
	                                        if (griglia[k][x] == 0){griglia[k][x] = 3; flag = true;}
	                                    }
	                                    if (l==j+1){
	                                        int x = l;
	                                        while (x<7 && griglia[k][x] == b){x++;}
	                                        if (griglia[k][x] == 0){griglia[k][x] = 3; flag = true;}
	                                    }
	                                }
	                                if (l==j && k!=0 && k!=7){
	                                    if (k==i-1){
	                                        int y = k;
	                                        while (y>0 && griglia[y][l] == b){y--;}
	                                        if (griglia[y][l] == 0){griglia[y][l] = 3; flag = true;}
	                                    }
	                                    if (k==i+1){
	                                        int y = k;
	                                        while (y<7 && griglia[y][l] == b){y++;}
	                                        if (griglia[y][l] == 0){griglia[y][l] = 3; flag = true;}
	                                    }
	                                }
	                                if (k==i-1 && l==j-1 && k>0 && l>0){
	                                    int x = l, y = k;
	                                    while (x>0 && y>0 && griglia[y][x]==b){x--;y--;}
	                                    if (griglia[y][x] == 0){griglia[y][x] = 3; flag = true;}
	                                }
	                                if (k==i+1 && l==j-1 && k<7 && l>0){
	                                    int x = l, y = k;
	                                    while (x>0 && y<7 && griglia[y][x]==b){x--;y++;}
	                                    if (griglia[y][x] == 0) {griglia[y][x] = 3; flag = true;}
	                                }
	                                if (k==i+1 && l==j+1 && k<7 && l<7){
	                                    int x = l, y = k;
	                                    while (x<7 && y<7 && griglia[y][x]==b){x++;y++;}
	                                    if (griglia[y][x] == 0) {griglia[y][x] = 3; flag = true;}
	                                }
	                                if (k==i-1 && l==j+1 && k>0 && l<7){
	                                    int x = l, y = k;
	                                    while (x<7 && y>0 && griglia[y][x]==b){x++;y--;}
	                                    if (griglia[y][x] == 0) {griglia[y][x] = 3; flag = true;}
	                                }
	                            }
	                    	}
	                    }
	                }
	            }
	        }
	    }
		
		return flag;
	}
		
	//esegue la mossa e cattura le pedine
	boolean mossa(int x, int y, int p) {
		
		boolean flag = false;
	    int g = 1;
	    if (p == 1) g = 2;
	    for (int i=x-1; i<=x+1; i++){
	        for (int j=y-1; j<=y+1; j++){
	            if (i>=0 && i<griglia.length && j>=0 && j<griglia[0].length && griglia[i][j]==g){
	                if (x==i){
	                    if (y==j-1){
	                        int k = j;
	                        int cont = 0;
	                        while (k<7 && griglia[i][k] == g){
	                        	k++; cont++;
	                        	}
	                        if (griglia[i][k]==p){
	                            while(cont > 0){
	                                griglia[i][k-cont] = p;
	                                cont--; flag = true;
	                            }
	                        }
	                    }
	                    if (y==j+1){
	                        int k = j;int cont=0;
	                        while (k>0 && griglia[i][k] == g){k--; cont++;}
	                        if (griglia[i][k] == p){
	                            while(cont > 0){
	                                griglia[i][k+cont] = p;
	                                cont--; flag = true;
	                            }
	                        }
	                    }
	                }
	                if (y==j){
	                    if (x==i-1){
	                        int k = i; int cont = 0;
	                        while (k<7 && griglia[k][j] == g){k++; cont++;}
	                        if (griglia[k][j] == p){
	                            while(cont > 0){
	                                griglia[k-cont][j] = p;
	                                cont--; flag = true;
	                            }
	                        }
	                    }
	                    if (x==i+1){
	                        int k = i; int cont=0;
	                        while (k>0 && griglia[k][j] == g){k--; cont++;}
	                        if (griglia[k][j] == p){
	                            while(cont > 0){
	                                griglia[k+cont][j] = p;
	                                cont--; flag = true;
	                            }
	                        }
	                    }
	                }
	                if (x==i-1 && y==j-1){
	                    int k = i, l = j, cont=0;
	                    while (k<7 && l<7 && griglia[k][l] == g){k++; l++; cont++;}
	                    if (griglia[k][l] == p){
	                        while(cont > 0){
	                            griglia[k-cont][l-cont] = p;
	                            cont--; flag = true;
	                        }
	                    }
	                }
	                if (x==i+1 && y==j-1){
	                    int k = i, l = j, cont=0;
	                    while (k>0 && l<7 && griglia[k][l] == g){k--; l++; cont++;}
	                    if (griglia[k][l] == p){
	                        while(cont > 0){
	                            griglia[k+cont][l-cont] = p;
	                            cont--; flag = true;
	                        }
	                    }
	                }
	                if (x==i+1 && y==j+1){
	                    int k = i, l = j, cont=0;
	                    while (k>0 && l>0 && griglia[k][l] == g){k--; l--; cont++;}
	                    if (griglia[k][l] ==p){
	                        while(cont > 0){
	                            griglia[k+cont][l+cont] = p;
	                            cont--; flag = true;
	                        }
	                    }
	                }
	                if (x==i-1 && y==j+1){
	                    int k = i, l = j, cont=0;
	                    while (k<7 && l>0 && griglia[k][l] == g){k++; l--; cont++;}
	                    if (griglia[k][l] == p){
	                        while(cont > 0){
	                            griglia[k-cont][l+cont] = p;
	                            cont--; flag = true;
	                        }
	                    }
	                }
	            }
	        }
	    }
	 
	    return flag;
	}

	private void executeIA() throws InterruptedException {
		
		//Thread.sleep(1000);
		
		ThreadIA thread = new ThreadIA();
		
		thread.start();
		
	}
	
	@Override
	public void mousePressed(MouseEvent evt) {
		
		//se e' il turno del giocatore
		if (player == 2) {
		
			int x = evt.getX();
			int y = evt.getY();
			
			//scorre la scacchiera e riconosce la cella cliccata
			for(int i=0; i<quadrati.length; i++) {
				for(int j=0; j<quadrati[0].length; j++) {
					if(quadrati[i][j].contains(evt.getPoint())) {
						//System.out.println(i+" "+j);
						
						//se la cella e' gialla
						if(griglia[i][j]==3) {
							
							//esegui la mossa
							griglia[i][j] = player;
							mossa(i,j,player);
							
							//scambia i giocatori
							if(player!=other) {
								player=other;
								System.out.println("Tocca al giocatore Bianco");
							}
							else {
								player=2;
								System.out.println("Tocca al giocatore Nero");
							}
							celleValide(player);
							break;
						}
					else {
						System.out.println("Mossa errata");
						}
					}
					
				}
			}
			
			//contatore per accertarsi che ci siano mosse esegubili
			int cont2 = 0;
			
			//si accerta che ci siano mosse disponibili
			for(int i=0; i<griglia.length; i++) {
				for(int j=0; j<griglia.length; j++) {
					if(griglia[i][j]==3) {
						cont2++;					
					}
					System.out.print(griglia[j][i]+ " ");
				}
				System.out.println();
			}
			System.out.println();
			
			//se non ci sono mosse disponibili, cambia giocatore, se non ne ha neanche l'altro, finisce la partita
			if (cont2==0) {
				if(player!=other) {
					player=other;
					System.out.println("Tocca al giocatore Bianco perché il Nero non ha mosse");
					repaint();
					try {
						executeIA();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				else {
					player=2;
					System.out.println("Tocca al giocatore Nero perché il Bianco non ha mosse");
					repaint();
					
				}
				if(!celleValide(player)) {
					System.out.println("Partita finita, nessuno dei due può muoversi");
				}
			}
			if(player==1 && partitaFinita()==0) {
				thinking = true;
			
				//ridisegna sul canvas
				repaint();
			
				try {
//				Thread.sleep(2000);
//				System.out.println("sleep finito");
					executeIA();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void restart() {
		addMouseListener(this);
		for(int i=0; i<griglia.length; i++) {
			for(int j=0; j<griglia[0].length; j++) {
				griglia[i][j]=0;				
			}
			
		}
		griglia[3][3]=1;
		griglia[4][4]=1;
		griglia[3][4]=2;
		griglia[4][3]=2;
		player=2;
		celleValide(player);
		repaint();
		
	}
	
	//gestisce solo il reset
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_R) {
			System.out.println(partitaFinita());
				System.out.println(partitaFinita());
				restart();
			
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			Main m=new Main();
			
		}
		
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private class ThreadIA extends Thread {
		
		List<Cell> celle = new ArrayList<Cell>();
		
		public void run() {
		
			handler = new DesktopHandler(new DLVDesktopService("lib/dlv.mingw.exe"));
			if(partitaFinita()==0) {
			try {
				
				Thread.sleep(500);
				
				InputProgram facts = new ASPInputProgram();
				facts.addFilesPath(settings);
				for (int i = 0; i < griglia.length; i++) {
					for(int j = 0; j < griglia[0].length; j++) {
						Cell cella = new Cell(i , j, griglia[i][j]);
						facts.addObjectInput(cella);
					}
				}
				
				handler.addProgram(facts);
				
				ASPMapper.getInstance().registerClass(Answer.class);
			
				AnswerSets answers = (AnswerSets) handler.startSync();
				
				AnswerSet answer = answers.getAnswersets().get(0);
				
				Answer risposta = null;
				
				for (Object obj : answer.getAtoms()) {
					if (obj instanceof Answer) {
						risposta = (Answer) obj;
						System.out.println("La non risposta e': " + risposta.getX() + " " + risposta.getY());
					}
				}
				
				if (risposta != null) {
					int xx = risposta.getX(); int yy = risposta.getY();
					griglia[xx][yy] = 1;
					mossa(xx, yy, 1);
					System.out.println("La risposta e': " + xx + " " + yy);
				}
				else {
					System.out.println("risposta e' null");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//scambia i giocatori
			if(player!=other) {
				player=other;
				System.out.println("Tocca al giocatore Bianco");
			}
			else {
				player=2;
				System.out.println("Tocca al giocatore Nero");
			}
			celleValide(player);
			
			//contatore per accertarsi che ci siano mosse esegubili
			int cont2 = 0;
			
			//si accerta che ci siano mosse disponibili
			for(int i=0; i<griglia.length; i++) {
				for(int j=0; j<griglia.length; j++) {
					if(griglia[i][j]==3) {
						cont2++;					
					}
					//System.out.print(griglia[j][i]+ " ");
				}
				//System.out.println();
			}
			//System.out.println();
			
			//se non ci sono mosse disponibili, cambia giocatore, se non ne ha neanche l'altro, finisce la partita
			if (cont2==0) {
				if(player!=other) {
					player=other;
					System.out.println("Tocca al giocatore Bianco perché il Nero non ha mosse");
					repaint();
					try {
						executeIA();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				else {
					player=2;
					System.out.println("Tocca al giocatore Nero perché il Bianco non ha mosse");
					repaint();
				}
				if(!celleValide(player)) {
					System.out.println("Partita finita, nessuno dei due può muoversi");
				}
				}
			}
			thinking = false;
			
			//ridisegna sul canvas
			repaint();
			
		}//RUN
		
	}//THREADIA
	
}//REVERSI
