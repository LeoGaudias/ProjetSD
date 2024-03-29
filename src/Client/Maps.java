package Client;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;


public class Maps implements GLEventListener {
	
	public GLCanvas canvas;
	
	ArrayList<Homme> list_hommes;
	boolean isinside = false;
	int width;
	int height; 
	public Maps(int w, int he, int nb_rectangle,ArrayList<Homme> l_h)
	{
		canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		canvas.setPreferredSize(new Dimension(w,he));

		width = w;
		height = he;
	
		list_hommes = l_h;
	}

	public void refresh(ArrayList<Homme> l) {
		list_hommes = l;
		System.out.println("refresh!!");
		canvas.repaint();
	}
	
	void drawRepere(GL2 gl)
	{
		// axe des x
		gl.glColor3d(1,0,0);
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex3d(0,0,0);
		gl.glVertex3d(1,0,0);
		gl.glEnd();

		// axe des y
		gl.glColor3d(0,1,0);
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex3d(0,0,0);
		gl.glVertex3d(0,1,0);
		gl.glEnd();
	}
	
	void drawSortieMap(GL2 gl)
	{
		Point p = new Point(list_hommes.get(0).arrivee.x,list_hommes.get(0).arrivee.y);
		gl.glColor3d(1, 0, 0);
		
		gl.glBegin(GL2.GL_QUADS);
		
		gl.glVertex3d((double)(p.x-10)/width,(double) (p.y+10)/height, 0);
		gl.glVertex3d((double)(p.x+10)/width,(double) (p.y+10)/height, 0);
		gl.glVertex3d((double)(p.x+10)/width,(double) (p.y-10)/height, 0);
		gl.glVertex3d((double)(p.x-10)/width,(double) (p.y-+10)/height, 0);
		
		gl.glEnd();
		gl.glColor3d(0, 0, 0);
	}
	
	void drawObstacle(GL2 gl)
	{
		 gl.glBegin(GL2.GL_QUADS);
		 
		 for(int i=0; i< list_hommes.get(0).obstacles.size(); i++) {
			 	gl.glVertex3d((double)list_hommes.get(0).obstacles.get(i).getCoord(0).x/width, (double)list_hommes.get(0).obstacles.get(i).getCoord(0).y/height, 0.);  // Top left
				gl.glVertex3d((double)list_hommes.get(0).obstacles.get(i).getCoord(1).x/width, (double)list_hommes.get(0).obstacles.get(i).getCoord(1).y/height, 0);  // Top right
				gl.glVertex3d((double)list_hommes.get(0).obstacles.get(i).getCoord(2).x/width, (double)list_hommes.get(0).obstacles.get(i).getCoord(2).y/height, 0);  // Bottom right
				gl.glVertex3d((double)list_hommes.get(0).obstacles.get(i).getCoord(3).x/width, (double)list_hommes.get(0).obstacles.get(i).getCoord(3).y/height, 0);
		 }
		 gl.glEnd();
	}
	
	void drawDeplacement(GL2 gl, int tailleDeplacement)
	{
		for(int i=0; i<list_hommes.size(); i++) {
			gl.glBegin(GL2.GL_LINE_STRIP);
			Random randcolor = new Random();
			gl.glColor3f(randcolor.nextInt(256)/255f, randcolor.nextInt(256)/255f, randcolor.nextInt(256)/255f);
		
			//int modulo = 1;
			
			for(int j=0; j<list_hommes.get(i).getParcours().size(); j++) {
				//if(modulo%(list_hommes.get(i).getAdn().size()/50)==0) {
				gl.glVertex3d((double)list_hommes.get(i).getParcours().get(j).x/width,(double) list_hommes.get(i).getParcours().get(j).y/height, 0);
				//}
				//modulo++;	
			}
			gl.glEnd();
	
		}
	}

	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		// GLU glu = GLU.createGLU(gl);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthMask(true);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		gl.glOrtho(-1,1,-1,1,0,0);
		// gluPerspective( 60, (float)width/height, 1, 100);

		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();

		// dessiner ici
		// ...

		drawRepere(gl);
		drawObstacle(gl);
		drawDeplacement(gl,16);
		drawSortieMap(gl);
		//glutSwapBuffers();
	}

	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	
}
