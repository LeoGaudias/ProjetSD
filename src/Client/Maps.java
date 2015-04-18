package Client;

import java.awt.Dimension;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
//import com.jogamp.opengl.glu.GLU;

public class Maps implements GLEventListener {
	
	public GLCanvas canvas;
	
	public Maps()
	{
		canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		canvas.setPreferredSize(new Dimension(800,600));
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
		//TODO
	}
	
	void drawDeplacement(GL2 gl, Homme h, int tailleDeplacement)
	{
		//TODO
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
