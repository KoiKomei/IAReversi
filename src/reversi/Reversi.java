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
	private boolean fatto=false;
	private int player=2;
	private int other=1;
	//quadrati=boxes
	//griglia=pieces
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
		celleValide(player);
		
		
	}
	
	
	public void paint(Graphics g) {
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
	
	

	@Override
	public void mouseReleased(MouseEvent arg0) {
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
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();
		int cont=0;
		
		for(int i=0; i<quadrati.length; i++) {
			for(int j=0; j<quadrati[0].length; j++) {
				if(quadrati[i][j].contains(evt.getPoint())) {
					System.out.println(i+" "+j);
					if(griglia[i][j]==3) {
						griglia[i][j]=player;
						mossa(i,j,player);
						if(player!=other) {
							player=other;
						}
						else {
							player=2;
						}
						celleValide(player);
						break;
					}
				else {
					cont++;
					System.out.println("Mossa errata");
					}
				}
				
			}
		}
		for(int i=0; i<griglia.length; i++) {
			for(int j=0; j<griglia.length; j++) {
				System.out.print(griglia[j][i]+ " ");
			}
			System.out.println();
		}
		System.out.println();
		repaint();
	}

	
}
