//为什么还是不能把坦克打消失，而且子弹也没有消失？？？？？？？？？




//现在是自能打出5课子弹而且当打中敌人后敌人会消失，但是就不能动了，而且会一直报错！
//原因是因为多了if(diren1.isLive==false){direns.remove(diren1);}
//为什 么不能从direns中除去diren1.即死亡的那个敌人坦克？？？？？？？

//因为条件没有控制好，，下面是当isLive=false应该改成isLive==false
//                      前者应事给他附值，而后者才是判断当它为false时，执行先面的代码！！！！
//可以发射子弹，子弹会消失，敌人也会消失，！子弹最多5颗！

//这里要加的功能是：子弹打到坦克时 的爆炸效果。。。。？？？

//这里已经可以实现敌人随机走动，而且可以随机发子弹，不过只能一发子弹，应该定一个子弹的size最好，方便更改
//int direnzidanSize=3;
//
package Tank99;
//继续上级没写


//基本上所有功能都已经完成了，还差一个空格键暂停和开始的功能没有加！
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class TankWar2 extends JFrame implements ActionListener,KeyListener
{
	static MyPanel2 mp;
	Mystate msp;
	//加一些菜单组件
	JMenuBar jmb;
	JMenu jm1;
	JMenuItem jmi1;
	JMenuItem jmi2;
	JMenuItem jmi3;
	JMenuItem jmi4;
	
	public TankWar2() 
	{
		jmb=new JMenuBar();
		jm1=new JMenu("游戏（G）");
		jmi1=new JMenuItem("开始游戏！");
		jmi1.addActionListener(this);
		jmi1.setMnemonic('G');
		jmi1.setActionCommand("game");
		jmi2=new JMenuItem("退出游戏（E）");
		jmi2.setMnemonic('E');
		jmi2.setActionCommand("exit");
		jmi2.addActionListener(this);
		jmi3=new JMenuItem("存盘退出！");
		jmi3.addActionListener(this);
		jmi3.setMnemonic('S');
		jmi3.setActionCommand("save");
		jmi4=new JMenuItem("继续上集");
		jmi4.addActionListener(this);
		jmi4.setMnemonic('A');
		jmi4.setActionCommand("again");
		
		
		this.setJMenuBar(jmb);//加入一个菜单条
		jmb.add(jm1);
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
//		mp=new MyPanel2();
//								//注意添加组件的顺序，不能还没有添加就设置监听
//								//注意组件添加的顺序！！！！！注意！
//		this.add(mp);
//		this.addKeyListener(mp);
//		
//		///启动mp线程
//		Thread t=new Thread(mp);
//		t.start();
		msp=new Mystate();
		Thread t1=new Thread(msp);
		t1.start();
		this.add(msp);
		
		this.setTitle("坦克大战！");
		this.setVisible(true);
		this.setSize(600, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setResizable(false);
		
	}
	
	public static void main (String [] args)
	{
		TankWar2 TankWar2=new TankWar2();
//		///启动mp线程							//并不是要在这里启动线程，以后哪里创建的在哪里启动线程
////		Thread t=new Thread(TankWar2.mp);
//		Thread t=new Thread(TankWar2.msp);
//		t.start();
	
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			if(e.getActionCommand().equals("game"))
			{
				this.remove(msp);
				mp=new MyPanel2("newgame");
				//注意添加组件的顺序，不能还没有添加就设置监听
				//注意组件添加的顺序！！！！！注意！
				this.add(mp);
				this.addKeyListener(mp);
				
				/////启动mp线程
				Thread t=new Thread(mp);
				t.start();
				this.setVisible(true);//这句话一定要写，是让面板显示，不然面板不会显示！
				try {
					jiLu.Duquwenjian();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
			//写退出 的信息
			else if(e.getActionCommand().equals("exit"))
			{
				//要在退出前保存信息
				jiLu.Tankwenjian();
			
				//达到退出的目的
				System.exit(0);
			}
			//存盘退出
			else if(e.getActionCommand().equals("save"))
			{
				//功能一：保存敌人，我的坦克的坐标，子弹等，方向等
				
//				baocunjiLu b1=new baocunjiLu();
//				b1.baocunjiLu().
//				new baocunjiLu().setDirens(mp.direns);
				jiLu j1=null;//先定义一个对象
				j1.setDirens(mp.direns);//用对象的setDirens的方法访问面板里的direns，就可以使jiLu里的
										//direns访问到敌人
				j1.jiLutank();//调用jiLu里的jiLutank方法
				
				//退出的功能 写（0）正常退出，，写（1）异常退出
				System.exit(0);
			}
			else if(e.getActionCommand().equals("again"))
			{   
				//存盘退出之后的，恢复上局游戏
				
				System.out.println("第一个位子！");
				//先移除之前的面板
				this.remove(msp);
				jiLu.getzuoBiao();//这里不能忘记调用一下这个方法，用于获得敌人坐标，方向内容
				//1.创建面板
				mp=new MyPanel2("again");
				//注意添加组件的顺序，不能还没有添加就设置监听
				//注意组件添加的顺序！！！！！注意！
				this.add(mp);
				this.addKeyListener(mp);
				
				/////启动mp线程
				Thread t=new Thread(mp);
				t.start();
				this.setVisible(true);//这句话一定要写，是让面板显示，不然面板不会显示！
				try {
					jiLu.Duquwenjian();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				 
			}
	}
	
//	class Zanting  extends Tank
//	{
//		public Zanting(int x, int y) {
//			super(x, y);
//			// TODO Auto-generated constructor stub
//		}
//		Zanting zanting;
//		
//		
//	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		//在这里写存储坦克的信息的代码
		
		
		
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
class Mystate extends JPanel implements Runnable
{
	int time=0;
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawRect(0, 0, 400, 300);
		if(time%3==0)
		{
		g.setColor(Color.blue);
		Font font=new Font("华文彩云",Font.BOLD,30);
		this.setFont(font);
		g.drawString("关卡   1", 150, 150);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
//		while(true)
//		{
//				try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//				time++;
//				
//				this.repaint();
//		}
	}
}

class MyPanel2 extends JPanel implements KeyListener,Runnable
{
	
	Mytank mytank;//定义我的坦克
	//定义敌人的坦克
	Vector<Diren> direns=new  Vector<Diren>();
	
	
	
	//定义在这里，初始化在构造函数中
		Image img1=null;
//		//定义我的坦克一个组，用于当子弹打到我时删除我的的坦克
//		Vector<Mytank> myatanks=new Vector<Mytank>();
//		这个方法有问题，，，，应该只用一个if语句判断就行了！详解在下面
	
		//创建炸弹组，向炸弹组中加入炸弹
		Vector<Zhadan> zhadans=new Vector<Zhadan>();
		//构建坦克位子，方向的集合
//		Vector<tankzuoBiao> tankzuobiaos=new Vector<>();
		
		int direnSize=5;
		String note;
	public MyPanel2(String note)
	{
		//我的坦克
		mytank=new Mytank(200,200);//我的坦克刚出来的位置（200,200）
		//判断是从新开始还是新游戏    要在前面定义，this才能找到
//		String note="newgame";
		this.note=note;
		
		if(this.note.equals("newgame"))
		{
			//这里是从新开始的游戏
			//敌人的坦克
			for(int i=0;i<direnSize;i++)
			{
				
				Diren diren1=new Diren((i+1)*50,0);
				diren1.setDirect(3);
				
				//把敌人传到Diren里面，用于敌人的功能
				diren1.setdirens(direns);
	//			direns.add(diren1);
				Thread t=new Thread(diren1);
				t.start();//启动线程
				//给敌人的坦克添加一个子弹
				Zidan direnzidan=new Zidan(diren1.x+20,diren1.y+30,3);
				//吧敌人的子弹加入到敌人子弹组中
				diren1.direnzidans.add(direnzidan);
				
				
				//因为子弹是一个线程，所以这里要启动一下这个线程
				Thread t2=new Thread(direnzidan);
				t2.start();
				
				direns.add(diren1);
				diren1.isTouchdiren();
				
				}
		}else if(this.note.equals("again"))
		{
//			jiLu.tankzuobiaos =new jiLu().getTankzuobiaos();
			//敌人的坦克
			for(int i=0;i<jiLu.tankzuobiaos.size();i++)
			{//这里是历遍所有的活着的敌人tank，把敌人的坐标和方向传给diren，就达到了恢复的效果
				System.out.println("第二个位置！");
				tankzuoBiao tankzuobiao=jiLu.tankzuobiaos.get(i);
				Diren diren1=new Diren(tankzuobiao.x,tankzuobiao.y);
				diren1.setDirect(tankzuobiao.direct);
				
				//把敌人传到Diren里面，用于敌人的功能
				diren1.setdirens(direns);
	//			direns.add(diren1);
				Thread t=new Thread(diren1);
				t.start();//启动线程
				//给敌人的坦克添加一个子弹
				Zidan direnzidan=new Zidan(diren1.x+20,diren1.y+30,3);
				//吧敌人的子弹加入到敌人子弹组中
				diren1.direnzidans.add(direnzidan);
				
				
				//因为子弹是一个线程，所以这里要启动一下这个线程
				Thread t2=new Thread(direnzidan);
				t2.start();
				
				direns.add(diren1);
				diren1.isTouchdiren();
		}
		
		
		
		//初始化炸弹···
		img1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/12.gif"));
		}
				
	
	}
	@SuppressWarnings("null")
	public void paint(Graphics g)
	{
		//画图的话一定要有顺序，否则会覆盖，或者出现爱你其他的问题！
		
		
		super.paint(g);//这句话不能！
		g.setColor(Color.PINK);
		g.fillRect(0, 0, 400,300);
	
		//画出我的坦克
		//这里得有改进了，当敌人的子弹打到我的时候，我的坦克就会死！！！
		if(mytank.isLive==true){
		this.DrawTank(mytank.getX(), mytank.getY(), g, mytank.getDirect(), 0);
		}
//			myatanks.add(mytank);
//		if(mytank.isLive==false)     //只要一个if语句，表明：如果isLive==true时，画出，也就是说，只有当mytank时活着的
//		{							//时候，才画这个mytank，就是说其他情况都不去画，比如死亡了就不画
//			
//			myatanks.remove(mytank);
//		}
		
		//画出敌人的坦克
			//这里改了一下，不是for(int i=0;i<direnSize;i++)，，不然
			//坦克会一直为3辆
		for(int i=0;i<this.direns.size();i++)
		{
		 Diren diren=direns.get(i);
			if(diren.isLive==true)
			{
				
			this.DrawTank(diren.getX(), diren.getY(), g, diren.getDirect(), 1);
			//在画出坦克的时候就画出子弹
			for(int j=0;j<diren.direnzidans.size();j++)
			{
				Zidan direnzidan=diren.direnzidans.get(j);
//				while(true)
//				{
				if(direnzidan.isLive)
				{
					
					//这里是 画出敌人的子弹，显示出来！
					g.fill3DRect(direnzidan.getX(), direnzidan.getY(), 3, 3,false);	
					
				}
				if(direnzidan.isLive==false)
				{
					diren.direnzidans.remove(direnzidan);
				}
//				if(diren.direnzidans.size()<1)
//				{
////					g.fill3DRect(direnzidan.getX(), direnzidan.getY(), 3, 3,false);	
//				}
				}
//			}
			}
			if(diren.isLive==false)
			{
				this.direns.remove(diren);
//				
////			this.DrawTank(diren1.getX()+50, diren1.getY()+50, g, diren1.getDirect(), 1);
			}
		}
		
			//我不知道为什么，但是问题就是出现在这里
			//为什么敌人的坦克不能在他islive==false的时候
			//除去他，为什么子弹可以？？？？？
			//这里是为什么啊？？？？？？
			
			//这里 的功能是使得打死的坦克从新出现，位置有 
		
		
		//注意这里 的顺序的问题，，一定要先有mytank后才能画自己的子弹，，一定要注意顺序！
		//注意这里 的顺序的问题，，一定要先有mytank后才能画自己的子弹，，一定要注意顺序！
		//注意这里 的顺序的问题，，一定要先有mytank后才能画自己的子弹，，一定要注意顺序！

		
//				画出子弹//从子弹组中去每一颗子弹
		
		
			//为什么这里不能用zidan作为名称？？？？
			//前面已经定义过zidan了！
		//注意这里的zidan2是新创建的，所以不在mytank内，是历遍向量里的每一个子弹对象，并付给这里的zidan2
			//因此下面的if（mytank.zidan2!=null），是错的，，，这里画的子弹并不是mytank来咯的子弹
			
			//总结：这里吧mytank里的zidan传到zidans组内，然后又创建了一个Zidan对象来取遍zidans组内的每一个子弹
			//所以在这里画的是zidan2，，而不是zidan。
			
		for(int i=0;i<mytank.zidans.size();i++)
		{
			Zidan zidan=mytank.zidans.get(i);
			if(zidan!=null||mytank.zidan.isLive==true)
			{
				g.fill3DRect(zidan.getX(), zidan.getY(), 3, 3,false);	
			}	
			if(zidan.isLive==false)
			{
				mytank.zidans.remove(zidan);
//				System.out.println("男都是这里111111");
			}
		}
		//画出炸弹的效果
				for(int i=0;i<zhadans.size();i++)
				{
					Zhadan zhadan=zhadans.get(i);
					if(zhadan.life>0)
					{
						g.drawImage(img1, zhadan.x, zhadan.y,30,30 ,this);
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						zhadan.sleep(100);
//						zhadans.remove(zhadan);
					}
					zhadan.lifeDown();
					if(zhadan.life==0)
					{
						zhadans.remove(zhadan);
					}
					
				}
				//画出旁边提示 的坦克
				this.DrawTank(50, 350, g, 0, 0);
				g.setColor(Color.black);
				g.drawString(jiLu.getZijiSize()+"", 100, 380);
				this.DrawTank(140, 350, g, 0, 1);
				g.setColor(Color.black);
				g.drawString(jiLu.getDirenSize()+"", 190, 380);
				g.drawString("已经消灭的敌人数量：\n"+(5-jiLu.getDirenSize()), 450, 80);
				this.DrawTank(450, 100, g, 0, 1);
		
	}
	public void DrawTank(int x,int y,Graphics g,int direct,int type)
	{
		switch(type)//type实际是指坦克的颜色，用颜色来区分坦克的类型
		{
		case 0:
			g.setColor(Color.yellow);
			break;
		case 1:
			g.setColor(Color.green);
			break;							//注意这个break，，一定别忘记写！！！！
		}
		switch(direct)//0  是向上，   1  是向右，      2  是向左，      3    是向下
		{
		case 0:
			//炮口向上
			g.fill3DRect(x,y, 10, 30,false);
			g.fill3DRect(x+30,y, 10, 30,false);
			g.fill3DRect(x+10,y+5, 20, 20,false);
			g.fillOval(x+10,y+5, 20, 20);
			g.drawLine(x+20,y+15, x+20, y-5);
			break;							//注意这个break，，一定别忘记写！！！！
		case 1:
			//炮口向Y右
			g.fill3DRect(x+5, y-5, 30, 10, false);
			g.fill3DRect(x+5, y+25, 30, 10, false);
			g.fill3DRect(x+10, y+5, 20, 20, false);
			g.fillOval(x+10, y+5, 20, 20);
			g.drawLine(x+20, y+15, x+40, y+15);
			break;							//注意这个break，，一定别忘记写！！！！
		case 2:
			//炮口向左
			g.fill3DRect(x+5, y-5, 30, 10, false);
			g.fill3DRect(x+5, y+25, 30, 10, false);
			g.fill3DRect(x+10, y+5, 20, 20, false);
			g.fillOval(x+10, y+5, 20, 20);
			g.drawLine(x+20, y+15, x, y+15);
			break;							//注意这个break，，一定别忘记写！！！！
		case 3:
			//炮口向下
			g.fill3DRect(x,y, 10, 30,false);
			g.fill3DRect(x+30,y, 10, 30,false);
			g.fill3DRect(x+10,y+5, 20, 20,false);
			g.fillOval(x+10,y+5, 20, 20);
			g.drawLine(x+20,y+15, x+20, y+35);
			break;							//注意这个break，，一定别忘记写！！！！
		}
	}
	
	public void shejidiren()
	{
	//在这里实施判断每一刻子弹是否和每一辆敌人的坦克相遇
			//因为这里经常重绘面板，所以
			for(int i=0;i<mytank.zidans.size();i++)
			{										//记住这里，isLive=true是给它附值，而这里应该是当它为flase时
													//就是isLive==false，这是判断当它为false时所做的事
				Zidan zidan=mytank.zidans.get(i);	//谨记！谨记！谨记！谨记！谨记！谨记！谨记！谨记！
				if(zidan.isLive==true)				//谨记！谨记！谨记！谨记！谨记！谨记！谨记！谨记！
				{
					for(int j=0;j<direns.size();j++)
					{
						Diren diren1=direns.get(j);//千万不能把这里的get(),里面写成i，，不能写成get(i)!
													//切记：不能写成gei(i)
						if(diren1.isLive==true)
						{
							this.Sejidiren(zidan,diren1);
//							System.out.println("此时的子弹是："+i+"此时的坦克是+"+j);
							
						}
					}
				}
			}
	}
	
	//敌人的子弹射击到我的坦克的时候，我 的坦克和敌人的子弹一起消失		
	public void direnzidansejimytank()
	{
		
		for(int n=0;n<direns.size();n++)
			{
				Diren diren2=direns.get(n);
				if(diren2.isLive==true)
				{
					for(int k=0;k<diren2.direnzidans.size();k++)
					
					{
						Zidan direndezidan=diren2.direnzidans.get(k);
						if(direndezidan.isLive==true)
							{
							
							if(mytank.isLive==true){
							//调用Sejidiren（）这个方法，，要修改一下，因为这个是射击我，共性是，都是子弹射击坦克
							this.Sejidiren(direndezidan, mytank);
							
							}
//								switch(mytank.direct)
//								{
//								case 0:
//								case 3://我的坦克向上和向下的时候
//									if(direndezidan.x>mytank.x||direndezidan.x<mytank.x+40||direndezidan.y>mytank.y||direndezidan.y<mytank.y+30)
//									{
//										
//										direndezidan.isLive=false;
//										mytank.isLive=false;
//										System.out.println("这里是哪里？");
//									}
//								case 1:
//								case 2://我的坦克向左或者向右的时候！！！
//									if(direndezidan.x>mytank.x||direndezidan.x<mytank.x+30||direndezidan.y>mytank.y||direndezidan.y<mytank.y+40)
//									{
//										direndezidan.isLive=false;
//										mytank.isLive=false;
//										System.out.println("这里是哪里？");
//									}
									
								}
							}
					}
					
//			//		Diren diren;
//					for(int k=0;k<diren.direnzidans.size();k++)
//					{										//记住这里，isLive=true是给它附值，而这里应该是当它为flase时
//						//就是isLive==false，这是判断当它为false时所做的事
//						Zidan zidan=diren.direnzidans.get(k);	//谨记！谨记！谨记！谨记！谨记！谨记！谨记！谨记！
//						if(zidan.isLive==true)				//谨记！谨记！谨记！谨记！谨记！谨记！谨记！谨记！
//						{
//							this.direnzidansejimytank(zidan, mytank);
//						}
//					}
				}
			}
		
		
//		if(direnzidan.isLive==true)
//			{
//				switch(mytank.direct)
//				{
//				case 0:
//				case 3://我的坦克向上和向下的时候
//					if(direnzidan.x>mytank.x||direnzidan.x<mytank.x+40||direnzidan.y>mytank.y||direnzidan.y<mytank.y+30)
//					{
//						
//						direnzidan.isLive=false;
//						mytank.isLive=false;
//						System.out.println("这里是哪里？");
//					}
//				case 1:
//				case 2://我的坦克向左或者向右的时候！！！
//					if(direnzidan.x>mytank.x||direnzidan.x<mytank.x+30||direnzidan.y>mytank.y||direnzidan.y<mytank.y+40)
//					{
//						direnzidan.isLive=false;
//						mytank.isLive=false;
//						System.out.println("这里是哪里？");
//					}
//					
//				}
//			}
//		}

	//写一官网我的子弹射击到敌人坦克的类
	
			public void Sejidiren(Zidan zidan,Tank tank)
			{
				switch(tank.direct)
				{//0,3是向上和下的，，所以打到的位置相差不大
				//判断子弹与敌人坦克是否相遇，如果相遇，就除去子弹和敌人的坦克
				//用isLive断定坦克或作子弹是否死亡
				case 0://这里是向上和向下的情况
				case 3:
					if(zidan.x>tank.x&&zidan.x<tank.x+40&&zidan.y>tank.y&&zidan.y<tank.y+30)
					{
						//子弹死亡，坦克死亡
						zidan.isLive=false;
						tank.isLive=false;
						jiLu.direnjianShao();
						jiLu.xiaomeiderenSize();
						//当坦克死亡时，新建炸弹，并加入到炸弹组中
						Zhadan zhadan=new Zhadan(tank.x,tank.y);
						zhadan.isLive=true;
						zhadans.add(zhadan);
//						try {
//							Thread.sleep(100);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						zhadan.isLive=false;
					}
				case 2://这里是向左和向右的情况
				case 1:
					if(zidan.x>tank.x&&zidan.x<tank.x+30&&zidan.y>tank.y&&zidan.y<tank.y+40)
					{	
						//子弹死亡，坦克死亡
						zidan.isLive=false;
						tank.isLive=false;
						jiLu.direnjianShao();
						jiLu.xiaomeiderenSize();
						//当坦克死亡时，新建炸弹，并加入到炸弹组中
						Zhadan zhadan=new Zhadan(tank.x,tank.y);
						zhadan.isLive=true;
						zhadans.add(zhadan);
//						try {									//目的是想让炸弹爆炸之后过一段时然后消失
//							Thread.sleep(100);					//但是没有成功？？？？？？
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						zhadan.isLive=false;
					}
					
				}
			}
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
@SuppressWarnings("null")
@Override
public void keyPressed(KeyEvent e) {
	if(e.getKeyCode()==KeyEvent.VK_UP)
	{	
		this.mytank.setDirect(0);
		this.mytank.setSpeed(5);
		this.mytank.MoveUp();
	}
	if(e.getKeyCode()==KeyEvent.VK_DOWN)
	{	
		this.mytank.setDirect(3);
		this.mytank.setSpeed(5);
		this.mytank.MoveDown();
	}
	if(e.getKeyCode()==KeyEvent.VK_LEFT)
	{	
		this.mytank.setDirect(2);
		this.mytank.setSpeed(5);
		this.mytank.MoveLeft();
	}
	if(e.getKeyCode()==KeyEvent.VK_RIGHT)
	{	
		this.mytank.setDirect(1);
		this.mytank.setSpeed(5);
		this.mytank.MoveRight();
	}
	if(e.getKeyCode()==KeyEvent.VK_J)
	{	
		System.out.println("现在的子弹的数量为："+this.mytank.zidans.size());
		if(mytank.zidans.size()<=4)
		{
		this.mytank.Seji();
		}
	}
	
	int time=0;
//	while(true)
//	{
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			++time;
			if(time%2==0)
			{
				/////////////////////////////////////////////////还没有写？？暂停，和继续！！！
				Zidan zidan = null;
				
				zidan.speed=0;
				Tank tank=null;
				tank.speed=0;
				
			}
		}
		this.repaint();
//	}
	
//		if(zidan.isLive==false)
//		{
//			mytank.zidans.remove(zidan2);
//		}
	// TODO Auto-generated method stub
}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void run()
{
	while(true)
	{
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		//判断敌人的子弹是否活着,,.应该是每一个坦克的子弹是否还活着，所以应该是for循环
		for(int i=0;i<this.direns.size();i++)
		{
			Diren diren=direns.get(i);
			if(diren.isLive==true)
			{	//如果子弹已经死亡
				//那么创建一个敌人的子弹，因为子弹方向的问题，所要用switch语句
					Zidan direnzidan=null;
				if(diren.direnzidans.size()<4)
				{
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					switch(diren.direct)
					{
					//向上
					case 0:
						//创建了一个子弹
						direnzidan=new Zidan(diren.x+20, diren.y-5,0);
						//把子弹加入到子弹组中,
						diren.direnzidans.add(direnzidan);
						break;
					//向右
					case 1:
						//创建一个子弹
						direnzidan=new Zidan(diren.x+40, diren.y+15,1);
						diren.direnzidans.add(direnzidan);
						break;
					//向左
					case 2:
						direnzidan=new Zidan(diren.x, diren.y+15,2);
						diren.direnzidans.add(direnzidan);
						break;
					//向下
					case 3:
						direnzidan=new Zidan(diren.x+20, diren.y+35,3);
						diren.direnzidans.add(direnzidan);
						break;
					
					
					}
					Thread t=new Thread(direnzidan);
					t.start();
					}
				
			}
		}
		
		//我的子弹是否射击到敌人的坦克,,前面写了一个方法，在这里调用一下就行了！
		this.shejidiren();
		//执行敌人的子弹是否集中我的坦克
		this.direnzidansejimytank();
		  
	
		
//		Zidan direnzidan = null;
//		this.direnzidansejimytank(direnzidan, mytank);
			//画面重绘
		this.repaint();
		}
	
	}
}

	






