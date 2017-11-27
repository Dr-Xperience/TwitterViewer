package org.chatteron.demoproject.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow
{

	private JFrame frame;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel = new JPanel();

	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JPanel panel_1 = new JPanel();

	private final JPanel panel3[] = new JPanel[10];
	private final JLabel label1[] = new JLabel[10];

	private final JPanel panel31[] = new JPanel[15];
	private final JLabel label11[] = new JLabel[15];

	private enum stateScrollBar
	{
		firstTime, fetchUpdate, doNothing;
	}

	private stateScrollBar sb1 = stateScrollBar.doNothing;
	private int mouseWheelRotationTab1 = 0;

	/**
	 * Create the application.
	 */
	public MainWindow()
	{
		initialize();
		frame.setVisible(true);
		// IDatabase db = Factory.getFactory().getSource().getDatabase();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		tabbedPane.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
			}
		});

		frame.getContentPane().add(tabbedPane);

		tabbedPane.addTab("Twitter Feed", null, scrollPane, null);

		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		// gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);

		tabbedPane.addTab("Twitter Notify", null, scrollPane_1, null);

		scrollPane_1.setViewportView(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		// gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		panel_1.setLayout(gbl_panel_1);

		GridBagConstraints gbcpanel[] = new GridBagConstraints[10];
		for (int i = 0; i < 10; ++i)
		{
			gbcpanel[i] = new GridBagConstraints();
			gbcpanel[i].weighty = 1.0;
			gbcpanel[i].insets = new Insets(0, 0, 5, 0);
			gbcpanel[i].fill = GridBagConstraints.BOTH;
			gbcpanel[i].gridx = 0;
			gbcpanel[i].gridy = i;

			panel3[i] = new JPanel();

			panel.add(panel3[i], gbcpanel[i]);

			label1[i] = new JLabel("New label");
			panel3[i].add(label1[i]);
		}

		GridBagConstraints gbcpanel1[] = new GridBagConstraints[15];
		for (int i = 0; i < 15; ++i)
		{
			gbcpanel1[i] = new GridBagConstraints();
			gbcpanel1[i].weighty = 1.0;
			gbcpanel1[i].anchor = GridBagConstraints.NORTH;
			gbcpanel1[i].insets = new Insets(0, 0, 5, 0);
			gbcpanel1[i].fill = GridBagConstraints.BOTH;
			gbcpanel1[i].gridx = 0;
			gbcpanel1[i].gridy = i;

			panel31[i] = new JPanel();
			panel_1.add(panel31[i], gbcpanel1[i]);

			label11[i] = new JLabel("New label" + i);
			panel31[i].add(label11[i]);
		}

		JScrollBar scrollBarTab1 = scrollPane.getVerticalScrollBar();

		JViewport viewportTab1 = scrollPane.getViewport();

		scrollPane.addMouseWheelListener(new MouseWheelListener()
		{

			@Override
			public void mouseWheelMoved(MouseWheelEvent e)
			{
				// System.err.println(++mouseWheelRotationTab1);
				if (e.getWheelRotation() < 0)
					++mouseWheelRotationTab1;

				if (scrollBarTab1.getValue() == 0 && sb1 == stateScrollBar.firstTime)
				{
					sb1 = stateScrollBar.fetchUpdate;
					System.err.println("ScrollBar first time " + scrollBarTab1.getValue());
					return;
				}

				if (scrollBarTab1.getValue() == 0 && sb1 == stateScrollBar.fetchUpdate && mouseWheelRotationTab1 >= 18)
				{
					mouseWheelRotationTab1 = 0;
					sb1 = stateScrollBar.doNothing;
					System.err.println("fetch Update");
					return;
				}

				// if(scrollBarTab1.getValue() ==0 && sb1 == stateScrollBar.fetchUpdate)
				// {
				// sb1 = stateScrollBar.doNothing;
				// System.out.println("fetch Update");
				// return;
				// }

			}

		});

		scrollBarTab1.addAdjustmentListener(new AdjustmentListener()
		{

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e)
			{

				if (e.getValue() != 0 && sb1 == stateScrollBar.doNothing)
				{
					sb1 = stateScrollBar.firstTime;
					System.err.println("ScrollBar Moved by" + e.getValue());
					return;
				}

				if (e.getValue() == 0 && sb1 == stateScrollBar.firstTime)
				{
					sb1 = stateScrollBar.fetchUpdate;
					System.err.println("ScrollBar first time " + e.getValue());
					return;
				}

				if (e.getValue() == 0 && sb1 == stateScrollBar.fetchUpdate)
				{
					sb1 = stateScrollBar.doNothing;
					System.err.println("fetch Update");
					return;
				}

			}
		});

		viewportTab1.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent e)
			{
				// if(scrollBarTab1Value == 0)
				// return;
				//
				// if(scrollBarTab1.getValue() == scrollBarTab1.getMinimum())
				// {
				// System.err.println("Is at minimum" + scrollBarTab1Value);
				// }
				// else if(scrollBarTab1.getValue()+scrollBarTab1.getVisibleAmount() ==
				// scrollBarTab1.getMinimum())
				// {
				// System.err.println("Is at Max");
				// }

				// System.err.println("ScrollBar get visible amount ::
				// "+scrollBarTab1.getVisibleAmount());

			}

		});

	}

}
