package Tank99;
import java.awt.Transparency;
import java.io.*;
import java.util.Vector;



//定义一个他博客的坐标集合，和方向，用于恢复上级游戏
class  tankzuoBiao
{
	int x;
	int y;
	int direct;
	
	public tankzuoBiao(int x,int y,int direct)//注意这里不是public void tankzuoBiao（），，因为有返回值，所以没有void
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
}


//用于记录的类，几路自己，敌人的人数等内容
class jiLu
{
			//把玩家打到的坦克数量保存，然后再保存到文件中
			private static  FileWriter fw=null;
			private static BufferedWriter bw=null;
			static Vector<Diren>direns=new Vector<>();
			static		Vector<tankzuoBiao> tankzuobiaos=new Vector<>();
			
			
			public Vector<tankzuoBiao> getTankzuobiaos() {
			return tankzuobiaos;
		}


		@SuppressWarnings("static-access")
		public void setTankzuobiaos(Vector<tankzuoBiao> tankzuobiaos) {
			this.tankzuobiaos = tankzuobiaos;
		}


			public static Vector<Diren> getDirens() {
				return direns;
			}


			public static void setDirens(Vector<Diren> direns) {
				jiLu.direns = direns;
			}

			//恢复tank的坐标 ，方向
			public static Vector<tankzuoBiao> getzuoBiao()
			{
				try {
					fr=new FileReader("D:/k.txt");
					br=new BufferedReader(fr);
					String s=br.readLine();
					direnSize=Integer.parseInt(s);//读了第一行，杀死敌人的总数的数量
					System.out.println(s);
					while((s=br.readLine())!=null)
					{
						//从文件中读取每一行的数据，即x,Y,direct
						String []xyz=s.split(" ");//用空格隔开的，去掉空格
						//数据变成了3份
						tankzuoBiao tankzuobiao=new tankzuoBiao(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]));//这里字符型还没有转成整数型
						tankzuobiaos.add(tankzuobiao);
						System.out.println("坦克坐标daxiao"+tankzuobiaos.size());
					}
					
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					try {
						br.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						fr.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return tankzuobiaos;
			}
			
			
			

			//记录敌人和自己的坦克的坐标和方向
			public static void jiLutank()
			{
				
				try 
				{
					fw=new FileWriter("D:/k.txt");//注意冒号是在中文还是英文的状态下写的
											//需要注意这一点！！！
					bw= new BufferedWriter(fw);
					bw.write(direnSize+""+"\r\n");//这里应该转换一下，
								//因为写出去的应该是string型，而此时为int型
								//所以得转一下型
					//用for循环便利所有坦克，并记录坐标，
					for(int i=0;i<direns.size();i++)
					{
						//取出每一个敌人坦克
						Diren diren=direns.get(i);
						if(diren.isLive==true)
						{
							//如果敌人存在，就把敌人的坐标写出到文件中
							String s=diren.x+" "+diren.y+" "+diren.direct+"\r\n";
							//在这里记录每个敌人的X，Y，和方向
							bw.write(s);
						}
								
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
			
			
			
			
			public static void Tankwenjian()
			{
				try 
				{
					fw=new FileWriter("D:/k.txt");//注意冒号是在中文还是英文的状态下写的
											//需要注意这一点！！！
					bw= new BufferedWriter(fw);
					bw.write(direnSize+"");//这里应该转换一下，
								//因为写出去的应该是string型，而此时为int型
								//所以得转一下型
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			//现在外面定义一下对象
				private static FileReader fr=null;
				private static BufferedReader  br=null;
				//写一个类读取保存的内容！
				public static void  Duquwenjian() throws IOException
				{
					try {
						fr=new FileReader("D:/k.txt");
						br=new BufferedReader(fr);
						String s=br.readLine();
						direnSize=Integer.parseInt(s);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally
					{
						try {
							br.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							fr.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			@SuppressWarnings("unused")
			private static int xiaomeiderenSize=0;
			private static int direnSize=5;
			public static int getDirenSize() {
				return direnSize;
			}
			public static void setDirenSize(int direnSize) {
				jiLu.direnSize = direnSize;
			}
			public static int getZijiSize() {
				return zijiSize;
			}
			public static void setZijiSize(int zijiSize) {
				jiLu.zijiSize = zijiSize;
			}
			private static int zijiSize=3;
			
			public static  void direnjianShao()
			{
				direnSize--;
			}
			public static  void zijijianShao()
			{
				zijiSize--;
			}
			public static void xiaomeiderenSize()
			{
				xiaomeiderenSize++;
			}
			
			
			
			
	
	
}


//所有的坦克具有的共性！
class Tank
{
	int direct=0;
	int speed=1;
	int color=0;
	boolean isLive=true;
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	int y=0;
	int x=0;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
}
//敌人的坦克
class Diren extends Tank implements Runnable
{  //  int i==1;
	//用于存储敌人的子弹
	Vector<Zidan> direnzidans=new Vector<Zidan>();
	//创建一个敌人的向量组，把Mypanel上的敌人向量组传到整合力来，然后判断敌人不相互护碰撞
	Vector<Diren> direns=new Vector<Diren>();
	
	Diren diren1=null;
//	int speed=3;
	int timesb=10;
	public Diren(int x,int y)
	{
		super(x,y);
	}
	
	//写一个方法把Mypanel上的direns传进来
	public void setdirens(Vector<Diren> vv)
	{
		this.direns=vv;
	}
	
	//写一个用判断敌人之间不想碰撞的方法
	public boolean isTouchdiren()
	{
		boolean b=false;
		
		//取遍所有的敌人坦克，但是要去除这个敌人坦克，就是本身这个敌人坦克
		for(int i=0;i<direns.size();i++)
		{
			Diren diren1=direns.get(i);
			if(diren1!=this)
			{
				switch(this.direct)
				{//敌人的方向，然后是其他敌人的方向
					case 0://本身敌人的坦克的方向，向上
						switch(diren1.direct)
						{
						case 0://另外的敌人的运动的方向，向上和向下
						case 3:
							 if(this.x+40>diren1.x&&this.x+40<diren1.x+40&&this.y>diren1.y&&this.y<diren1.y+30)
							 {
								  return b=true;
							 }
							 if(this.x+40>diren1.x&&this.x+40<diren1.x+40&&this.y+30>diren1.y&&this.y+30<diren1.y+30)
							 {
								 return b=true;
							 }
							break;
						case 1://向左和向右
						case 2:
							 if(this.x+40>diren1.x&&this.x+40<diren1.x+30&&this.y>diren1.y&&this.y<diren1.y+40)
							 {
								 return b=true;
							 }
							 if(this.x+40>diren1.x&&this.x+40<diren1.x+30&&this.y+30>diren1.y&&this.y+30<diren1.y+40)
							 {
								 return b=true;
							 }
							
							break;
						}
						break;
					case 1:
						switch(diren1.direct)
						{
						case 0://另外的敌人的运动的方向，向上和向下
						case 3:
							 if(this.x+30>diren1.x&&this.x+30<diren1.x+40&&this.y>diren1.y&&this.y<diren1.y+30)
							 {
								 return b=true;
							 }
							 if(this.x+30>diren1.x&&this.x+30<diren1.x+40&&this.y+40>diren1.y&&this.y+40<diren1.y+30)
							 {
								 return b=true;
							 }
							break;
						case 1://向左和向右
						case 2:
							 if(this.x+30>diren1.x&&this.x+30<diren1.x+30&&this.y>diren1.y&&this.y<diren1.y+40)
							 {
								 return b=true;
							 }
							 if(this.x+30>diren1.x&&this.x+30<diren1.x+30&&this.y+40>diren1.y&&this.y+40<diren1.y+40)
							 {
								 return b=true;
							 }
							
							break;
						}
						break;
					case 2:
						switch(diren1.direct)
						{
						case 0://另外的敌人的运动的方向，向上和向下
						case 3:
							 if(this.x>diren1.x&&this.x<diren1.x+40&&this.y>diren1.y&&this.y<diren1.y+30)
							 {
								 return b=true;
							 }
							 if(this.x+40>diren1.x&&this.x+40<diren1.x+30&&this.y+40>diren1.y&&this.y+40<diren1.y+30)
							 {
								 return b=true;
							 }
							break;
						case 1://向左和向右
						case 2:
							 if(this.x>diren1.x&&this.x<diren1.x+30&&this.y>diren1.y&&this.y<diren1.y+40)
							 {
								 return b=true;
							 }
							 if(this.x>diren1.x&&this.x<diren1.x+30&&this.y+40>diren1.y&&this.y+40<diren1.y+40)
							 {
								 return b=true;
							 }
							
							break;
						}
						
						break;
					case 3:
						switch(diren1.direct)
						{
						case 0://另外的敌人的运动的方向，向上和向下
						case 3:
							 if(this.x>diren1.x&&this.x<diren1.x+40&&this.y+30>diren1.y&&this.y+30<diren1.y+30)
							 {
								 return b=true;
							 }
							 if(this.x+40>diren1.x&&this.x+40<diren1.x+40&&this.y+30>diren1.y&&this.y+30<diren1.y+30)
							 {
								 return b=true;
							 }
							break;
						case 1://向左和向右
						case 2:
							 if(this.x>diren1.x&&this.x<diren1.x+30&&this.y+30>diren1.y&&this.y+30<diren1.y+40)
							 {
								 return b=true;
							 }
							 if(this.x+40>diren1.x&&this.x+40<diren1.x+30&&this.y+30>diren1.y&&this.y+30<diren1.y+40)
							 {
								 return b=true; 
							 }
							
							break;
						}
						
						break;
				
				
				}
			}
		}
		
		return b;
	}
	
	
	@Override
	public void run() {
		//敌人会自动走动，即坐标有变化，所以应该用线程
		while(true)
		{
			
			
			switch(this.direct)//为什么这里不能 写成(diren1.direct)?????
			{
			case 0://向上
				for(int i=0;i<timesb;i++)
				{
					if(y>=0&&!this.isTouchdiren())
					{y-=speed;}
					
					try {//应该先休息一下不然，在很短的时间里地热会走很多地方
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 1://向右
				for(int i=0;i<timesb;i++)
				{
					if(x<=370&&!this.isTouchdiren())
					{x+=speed;}
					
					try {//应该先休息一下不然，在很短的时间里地热会走很多地方
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 2://向左
				for(int i=0;i<timesb;i++)
				{
					if(x>=0&&!this.isTouchdiren())
					{
					x-=speed;
					}
					try {//应该先休息一下不然，在很短的时间里地热会走很多地方
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 3://向下
				for(int i=0;i<timesb;i++)
				{
					if(y<=270&&!this.isTouchdiren())
					{y+=speed;}
					
					try {//应该先休息一下不然，在很短的时间里地热会走很多地方
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			
			}
			//随机产生一个数值，代表方向
			this.direct=(int)(Math.random()*4);
			//因为取整，所以这4个数是0,1,2,3
			
			//如果敌人的坦克死亡
			if(this.isLive==false)
			{	//让坦克死亡后退出线程
				break;
			}
					
			
			
		}
		
		
		
	}
}
//炸弹类，，不会移动，所以不用做成线程
class Zhadan 
{
	int x;
	int y;
	boolean isLive=false;
	int life=3;
	public Zhadan(int x,int y)
	{	
		this.x=x;
		this.y=y;
		
	}
	public void lifeDown()
	{
		life=life-3;
	}

	
}

//我的坦克
class Mytank extends Tank
{
	

	//窗建一个向量，组存放一个个子弹
	
	//初始化一个子弹
//	Zidan zidan2=null;
	Zidan zidan=null;
	Vector<Zidan> zidans=new Vector<Zidan>();
//	boolean isLive=true;
	public Mytank(int x,int y)
	{
		super(x,y);

	}
	//发射子弹
	public void Seji()
	{
				try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		switch(this.direct)
		{//向上
		case 0:
			//创建了一个子弹
			zidan=new Zidan(x+20, y-5,0);
			//把子弹加入到子弹组中,
			zidans.add(zidan);
			break;
		//向右
		case 1:
			//创建一个子弹
			zidan=new Zidan(x+40, y+15,1);
			zidans.add(zidan);
			break;
		//向左
		case 2:
			zidan=new Zidan(x, y+15,2);
			zidans.add(zidan);
			break;
		//向下
		case 3:
			zidan=new Zidan(x+20, y+35,3);
			zidans.add(zidan);
			break;
			
		}
		//子弹坐标有没有变化         //这里实际是坦克本身的位置，并不是子弹的原来的位置!//左上角
//		System.out.println("子弹的x是"+x+"   y是"+y);
		
		//启动子弹线程
		Thread Q=new Thread(zidan);
		Q.start();	
	}
	
	public void MoveUp()
	{
		y-=speed;
	}
	public void MoveDown()
	{
		y+=speed;
	}
	public void MoveLeft()
	{
		x-=speed;
	}
	
	public void MoveRight()
	{
		x+=speed;
	}
}
//坦克的子弹
class Zidan implements Runnable  
{	
	int speed=2;
	Zidan zidan=null;
	int direct;
	
	//判断子弹是否死亡
	boolean isLive=true;
	
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	int x;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	int y;
	public Zidan(int x,int y,int direct)
	{
		
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	public void  run()
	{
		
		while(true)
		{
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			switch(direct)
			{	//向上
				case 0:
					y=y-speed;
					break;
				case 1:
					x+=speed;
					break;
				case 2:
					x-=speed;
					break;
				case 3:
					y+=speed;
					break;
			}
	//		System.out.println("此时的坐标是x"+x+"|y="+y);
			
			//子弹何时死亡？要写。。。
			if(x<=0||y<=0||x>=400||y>=300)
			{
				this.isLive=false;
				
				break;     
			}
			//自要引入子弹类，那么所有的子弹就都具有子弹的共性，比如子弹何时死亡
			//无论是我的坦克发出的子弹，还是敌人的tank发出的子弹都具有这一个共性
			
			
			
		
		
		}
		
		
		
	}
}


