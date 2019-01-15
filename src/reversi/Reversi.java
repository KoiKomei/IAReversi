package reversi;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Reversi extends JPanel implements MouseListener{

	private int [][]griglia=new int[8][8];;
	private Rectangle [][]quadrati=new Rectangle[8][8];;
	
	Reversi() {
		
		
		addMouseListener(this);
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
		
		celleValide(griglia,2);
		repaint();
		//prova
		//prova2
		/*disegnaMappa(griglia);
		
		while(partitaFinita(griglia)==0) {
			boolean fatto=false;
			boolean canMove=celleValide(griglia,2);
			//funzione per dire a chi tocca
			disegnaMappa(griglia);
			int x=0, y=0;
			boolean mossaValida=false;
			boolean dodge=false;
			
			if(canMove) {
				while(!mossaValida) {
					while(!fatto) {
						/*funzione per cliccare e chiudere la partita*
						
					}
					
					
					int a=0, b=0;
					
					for(int i=0; i<8; i++) {
						if(x>=512/8*i && x<512/8*(i+1))	
							a=i;
					}
					for(int i=0; i<8; i++) {
						if(y>=600/8*i && y<600/8*(i+1))
							b=i;
					}
					if(a>=0 && a<8 && b>=0 && b<8 && griglia[a][b]==3) {
						griglia[a][b]=2;
						mossa(a,b,2,griglia);
						mossaValida=true;
					}
					else {
						
						//messaggio per mossa non valida
						fatto=false;
					}
						
				}
				
			}
			else {
				//Il nero non può muoversi, time to dodge
				dodge=true;
				
			}
			mossaValida=false;
			fatto=false;
			
			if(canMove) {
				while(!mossaValida) {
					while(!fatto) {
						/*funzione per cliccare e chiudere la partita*
						
					}
					
					
					int a=0, b=0;
					
					for(int i=0; i<8; i++) {
						if(x>=512/8*i && x<512/8*(i+1))	
							a=i;
					}
					for(int i=0; i<8; i++) {
						if(y>=512/8*i && y<512/8*(i+1))
							b=i;
					}
					if(a>=0 && a<8 && b>=0 && b<8 && griglia[a][b]==3) {
						griglia[a][b]=1;
						mossa(a,b,1,griglia);
						mossaValida=true;
					}
					else {
						
						//messaggio per mossa non valida
						fatto=false;
					}
						
				}
				
			}
			else {
				//Il bianco non può muoversi, time to dodge
				fatto=false;
				if(dodge) {break;}
				
			}
			dodge=false;
				
		}
		disegnaMappa(griglia);
		
		if(partitaFinita(griglia)==1) {/*Messaggio vittoria nero*}
		if(partitaFinita(griglia)==2) {/*Messaggio vittoria bianco*}
		else {
			int contB=0, contN=0;
			for (int i=0; i<8; i++){
	            for (int j=0; j<8; j++){
	                if (griglia[i][j] == 1) contB++;
	                if (griglia[i][j] == 2) contN++;
	            }
	        }
			if(contB>contN) {/*Messaggio vittoria bianco*}
			if(contN>contB) {/*Messaggio vittoria nero*}
			if(contB==contN) {/*Messaggio pareggio*}
			
		}*/
	}
	
	
	public void paint(Graphics g) {
		/*g.setColor(Color.BLACK);
			g.fillRect(100, 100, 401, 401);
			for(int i=100; i<500; i+=100) {
					for(int j=100; j<=400; j+=100) {
							g.fillRect(i, j, 50, 50);
					}
			}	
			g.setColor(Color.GREEN);
			for(int i=101; i<500; i+=50) {
				for(int j=101; j<=500; j+=50) {
						g.fillRect(i, j, 49, 49);
					
				}
				
			}
			
			
		*/
		int contB=0, contN=0;
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
						g.setColor(Color.BLACK);
						contN++;
					}
					if(griglia[i][j]==2) {
						g.setColor(Color.WHITE);
						contB++;
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
	
		
	}
	
	
	
	int partitaFinita(int griglia[][]) {
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
	
	boolean celleValide(int griglia[][], int a) {
		for(int i=0; i<8; i++) {
				for(int j=0; j<8; j++) {
					if(griglia[i][j]==3)
						griglia[i][j]=0;
				}
			
		}
		
		int b=1;
		if(a==1) b=2;
		boolean flag=false;
		
		for (int i=0; i<8; i++){
	        for (int j=0; j<8; j++){
	            if (griglia[i][j] == a){
	                for (int k=i-1; k<=i+1; k++){
	                    for (int l=j-1; l<=j+1; l++){
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
		
		
		return flag;
		}
	
	
	void disegnaMappa(int griglia[][]) {
		int contN=0, contB=0;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(griglia[i][j]==1) {
					contB++;
					//inserire funzione per disegnare un pallino bianco					
				}
				if(griglia[i][j]==2) {
					contN++;
					//inserire funzione per disegnare un pallino nero
					
				}
				if(griglia[i][j]==3) {
					//inserire funzione per disegnare un pallino giallo
					
				}
				
			}
			
		}
		/*Necessità di zone per il punteggio
		 * bianco
		 * nero
		*/
	}
	
	boolean mossa(int x, int y, int player, int griglia[][]) {
		
		boolean flag = false;
	    int g = 1;
	    if (player == 1) g = 2;
	    for (int i=x-1; i<=x+1; i++){
	        for (int j=y-1; j<=y+1; j++){
	            if (i>=0 && i<8 && j>=0 && j<8 && griglia[i][j]==g){
	                if (x==i){
	                    if (y==j-1){
	                        int k = j; int cont = 0;
	                        while (k<7 && griglia[i][k] == g){k++; cont++;}
	                        if (griglia[i][k] == player){
	                            while(cont > 0){
	                                griglia[i][k-cont] = player;
	                                cont--; flag = true;
	                            }
	                        }
	                    }
	                    if (y==j+1){
	                        int k = j;int cont=0;
	                        while (k>0 && griglia[i][k] == g){k--; cont++;}
	                        if (griglia[i][k] == player){
	                            while(cont > 0){
	                                griglia[i][k+cont] = player;
	                                cont--; flag = true;
	                            }
	                        }
	                    }
	                }
	                if (y==j){
	                    if (x==i-1){
	                        int k = i; int cont = 0;
	                        while (k<7 && griglia[k][j] == g){k++; cont++;}
	                        if (griglia[k][j] == player){
	                            while(cont > 0){
	                                griglia[k-cont][j] = player;
	                                cont--; flag = true;
	                            }
	                        }
	                    }
	                    if (x==i+1){
	                        int k = i; int cont=0;
	                        while (k>0 && griglia[k][j] == g){k--; cont++;}
	                        if (griglia[k][j] == player){
	                            while(cont > 0){
	                                griglia[k+cont][j] = player;
	                                cont--; flag = true;
	                            }
	                        }
	                    }
	                }
	                if (x==i-1 && y==j-1){
	                    int k = i, l = j, cont=0;
	                    while (k<7 && l<7 && griglia[k][l] == g){k++; l++; cont++;}
	                    if (griglia[k][l] == player){
	                        while(cont > 0){
	                            griglia[k-cont][l-cont] = player;
	                            cont--; flag = true;
	                        }
	                    }
	                }
	                if (x==i+1 && y==j-1){
	                    int k = i, l = j, cont=0;
	                    while (k>0 && l<7 && griglia[k][l] == g){k--; l++; cont++;}
	                    if (griglia[k][l] == player){
	                        while(cont > 0){
	                            griglia[k+cont][l-cont] = player;
	                            cont--; flag = true;
	                        }
	                    }
	                }
	                if (x==i+1 && y==j+1){
	                    int k = i, l = j, cont=0;
	                    while (k>0 && l>0 && griglia[k][l] == g){k--; l--; cont++;}
	                    if (griglia[k][l] == player){
	                        while(cont > 0){
	                            griglia[k+cont][l+cont] = player;
	                            cont--; flag = true;
	                        }
	                    }
	                }
	                if (x==i-1 && y==j+1){
	                    int k = i, l = j, cont=0;
	                    while (k<7 && l>0 && griglia[k][l] == g){k++; l--; cont++;}
	                    if (griglia[k][l] == player){
	                        while(cont > 0){
	                            griglia[k-cont][l+cont] = player;
	                            cont--; flag = true;
	                        }
	                    }
	                }
	            }
	        }
	    }
	    return flag;
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
