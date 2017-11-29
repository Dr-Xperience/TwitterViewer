package org.chatteron.demoproject.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayDeque;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.chatteron.demoproject.factory.Factory;
import org.chatteron.demoproject.factory.exceptions.FactoryException;
import org.chatteron.demoproject.io.listeners.TweetStreamListener;
import org.chatteron.demoproject.json.TwitterStreamJSON;

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
	private final JPanel panel_2 = new JPanel();
	private final JLabel lblEnterAWord = new JLabel("Enter a word to search on Twitter");
	private final JTextField textField = new JTextField();
	private final JScrollPane scrollPane_2 = new JScrollPane();
	private final JTextPane textPane = new JTextPane();
	private final JButton btnSearch = new JButton("Search");

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
		textField.setColumns(10);
		frame = new JFrame();
		frame.setBounds(100, 100, 543, 339);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		tabbedPane.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
			}
		});

		frame.getContentPane().add(tabbedPane);

		tabbedPane.addTab("Twitter Search (Streaming)", null, panel_2, null);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 222, 229, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 35, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		GridBagConstraints gbc_lblEnterAWord = new GridBagConstraints();
		gbc_lblEnterAWord.fill = GridBagConstraints.BOTH;
		gbc_lblEnterAWord.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterAWord.gridx = 0;
		gbc_lblEnterAWord.gridy = 0;
		panel_2.add(lblEnterAWord, gbc_lblEnterAWord);

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_2.add(textField, gbc_textField);

		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.BOTH;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 2;
		gbc_btnSearch.gridy = 0;
		btnSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Factory.getFactory().getSource().getTwitterStream().streamData(textField.getText());
					Factory.getFactory().getSource().getTwitterStream()
							.addTweetStreamListerner(new TweetStreamListener()
							{

								@Override
								public void streamRecieved(ArrayDeque<TwitterStreamJSON> ad)
								{
									for (TwitterStreamJSON j : ad)
									{
										StyledDocument doc = textPane.getStyledDocument();

										// Define a keyword attribute

										SimpleAttributeSet keyWord = new SimpleAttributeSet();										
										StyleConstants.setBackground(keyWord, Color.YELLOW);
										StyleConstants.setBold(keyWord, true);
										
										SimpleAttributeSet keyWord1 = new SimpleAttributeSet();
										StyleConstants.setBold(keyWord, false);
										

										// Add some text

										try
										{											
											doc.insertString(doc.getLength(),j.getName() , keyWord);
											doc.insertString(doc.getLength(),j.getText() , keyWord1);
										}
										catch (Exception e)
										{
											System.out.println(e);
										}

									}

								}
							});
				}
				catch (URISyntaxException | SQLException | FactoryException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_2.add(btnSearch, gbc_btnSearch);

		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 3;
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 1;
		panel_2.add(scrollPane_2, gbc_scrollPane_2);
		textPane.setEditable(false);
		textPane.setContentType("text/html");

		scrollPane_2.setViewportView(textPane);

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
