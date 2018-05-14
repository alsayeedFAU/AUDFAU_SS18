
// ***** DO NOT USE, CHANGE OR TRY TO UNDERSTAND THE CODE BELOW ***** //
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class KochSnowflakeGUI {
	public static void main(String[] args) {
		new KochscheSchneeflockeInnerGUI(args);
	}

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable t) {
		}
	}

	private KochSnowflakeGUI() {
	}

	private static final class KochscheSchneeflockeInnerGUI extends JFrame implements MouseListener, MouseWheelListener, MouseMotionListener {
		private static final long serialVersionUID = 666L;
		private static final Color FILLCOLOR = new Color(128, 255, 128);
		private static final Color LINECOLOR = Color.BLACK;
		private LinkedList<Double> xs = new LinkedList<>(), ys = new LinkedList<>();
		private int iterationen = 5;
		private JPanel canvas;
		private JScrollPane canvasScrollPane;
		private int mouseStartX, mouseStartY;
		private double canvasScale = 1;
		private KochSnowflakeCanvas zeichenflaeche = new KochSnowflakeCanvas() {
			public void drawLine(double xStart, double yStart, double xEnde, double yEnde) {
				xs.add(xStart);
				ys.add(yStart);
				xs.add(xEnde);
				ys.add(yEnde);
			}
		};

		private KochscheSchneeflockeInnerGUI(String[] args) {
			super("Kochsche Schneeflocke");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			KochSnowflake.draw(zeichenflaeche, 0, 0, 15, 25.980762114, iterationen);
			KochSnowflake.draw(zeichenflaeche, 15, 25.980762114, 30, 0, iterationen);
			KochSnowflake.draw(zeichenflaeche, 30, 0, 0, 0, iterationen);
			canvas = new JPanel() {
				private static final long serialVersionUID = 42L;

				@Override
				public void paint(Graphics g) {
					setBackground(Color.WHITE);
					super.paint(g);
					Double[] xsFreeze = xs.toArray(new Double[0]);
					Double[] ysFreeze = ys.toArray(new Double[0]);
					double minX = 0, minY = 0, maxX = 0, maxY = 0;
					for (double x : xsFreeze) {
						minX = x < minX ? x : minX;
						maxX = x > maxX ? x : maxX;
					}
					for (double y : ysFreeze) {
						minY = y < minY ? y : minY;
						maxY = y > maxY ? y : maxY;
					}
					double deltaX = maxX - minX, deltaY = maxY - minY;
					double scale = getWidth() / deltaX < getHeight() / deltaY ? getWidth() / deltaX : getHeight() / deltaY;
					Polygon p = new Polygon();
					for (int i = 0; i < xsFreeze.length; i++) {
						int x = (int) ((xsFreeze[i] - minX) * scale);
						int y = getHeight() - (int) ((ysFreeze[i] - minY) * scale) - 2;
						p.addPoint(x, y);
					}
					g.setColor(FILLCOLOR);
					g.fillPolygon(p);
					g.setColor(LINECOLOR);
					g.drawPolygon(p);
					if (g instanceof Graphics2D) {
						((Graphics2D) g).scale(canvasScale, canvasScale);
					}
				}
			};
			canvas.setPreferredSize(new Dimension(1000, 1000));
			canvas.addMouseListener(this);
			canvas.addMouseWheelListener(this);
			canvas.addMouseMotionListener(this);
			getContentPane().add(canvasScrollPane = new JScrollPane(canvas));
			pack();
			setVisible(true);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == canvas && e.getButton() == MouseEvent.BUTTON1 && iterationen < 666) {
				iterationen++;
			} else if (e.getSource() == canvas && e.getButton() == MouseEvent.BUTTON3 && iterationen > 0) {
				iterationen--;
			}
			xs.clear();
			ys.clear();
			KochSnowflake.draw(zeichenflaeche, 0, 0, 15, 25.980762114, iterationen);
			KochSnowflake.draw(zeichenflaeche, 15, 25.980762114, 30, 0, iterationen);
			KochSnowflake.draw(zeichenflaeche, 30, 0, 0, 0, iterationen);
			canvas.repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			mouseStartX = e.getX();
			mouseStartY = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			canvasScale -= e.getPreciseWheelRotation() / 5;
			if (canvasScale < 1) {
				canvasScale = 1;
			}
			Dimension newSize = new Dimension((int) (1000 * canvasScale), (int) (1000 * canvasScale));
			canvas.setSize(newSize);
			canvas.setPreferredSize(newSize);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			Rectangle view = canvasScrollPane.getViewport().getViewRect();
			view.x += mouseStartX - e.getX();
			view.y += mouseStartY - e.getY();
			canvas.scrollRectToVisible(view);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}
}