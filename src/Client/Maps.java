package Client;

import java.awt.Dimension;
import java.awt.Point;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class Maps implements GLEventListener {
	
	public GLCanvas canvas;
	
	static Homme h;
	boolean isinside = false;
	int width;
	int height; 
	public Maps(int w, int he, int nb_rectangle,Homme homme)
	{
		canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		canvas.setPreferredSize(new Dimension(w,he));

		width = w;
		height = he;
	
		
		h = homme;
	}
	
	void drawRepere(GL2 gl)
	{
		// axe des x
		gl.glColor3d(1,0,0);
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex3d(0,0,0);
		gl.glVertex3d(400,0,0);
		gl.glEnd();

		// axe des y
		gl.glColor3d(0,1,0);
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex3d(0,0,0);
		gl.glVertex3d(0,300,0);
		gl.glEnd();
	}
	
	void drawSortieMap(GL2 gl)
	{
		//TODO
	}
	
	void drawObstacle(GL2 gl)
	{
		 gl.glBegin(GL2.GL_QUADS);
		 
		 for(int i=0; i< h.obstacles.size(); i++) {
			 	gl.glVertex3d((double)h.obstacles.get(i).getCoord(0).x/width, (double)h.obstacles.get(i).getCoord(0).y/height, 0.);  // Top left
				gl.glVertex3d((double)h.obstacles.get(i).getCoord(1).x/width, (double)h.obstacles.get(i).getCoord(1).y/height, 0);  // Top right
				gl.glVertex3d((double)h.obstacles.get(i).getCoord(2).x/width, (double)h.obstacles.get(i).getCoord(2).y/height, 0);  // Bottom right
				gl.glVertex3d((double)h.obstacles.get(i).getCoord(3).x/width, (double)h.obstacles.get(i).getCoord(3).y/height, 0);
		 }
		 gl.glEnd();
	}
	
	void drawDeplacement(GL2 gl, Homme h, int tailleDeplacement)
	{
		gl.glBegin(GL2.GL_LINES);
		 Point p = new Point(-600,0); //point de départ
		 gl.glVertex3d((double)p.x/width, (double)p.y/height, 0);
		 for(int i=0; i<h.getAdn().size(); i++) {
			 
			 h.setPositionCourante(p, i);
			/* if(!isinside) {
					for(int j=0; j<h.obstacles.size(); j++) {
						if(tab_rectangles[j].isInside(p)) {
							System.out.println("collision");
							h.mort = true;
							isinside = true;
							break;
						}
					}*/
					gl.glVertex3d((double)p.x/width,(double) p.y/height, 0);
				//}
		 }
		// isinside = false;
		 gl.glEnd();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		// GLU glu = GLU.createGLU(gl);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthMask(true);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		gl.glOrtho(-400,400,-300,300,0,0);
		// gluPerspective( 60, (float)width/height, 1, 100);

		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();

		// dessiner ici
		// ...

		drawRepere(gl);
		drawObstacle(gl);
		drawDeplacement(gl,h,16);
		//glutSwapBuffers();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	
}
