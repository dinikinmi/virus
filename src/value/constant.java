package value;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import dataBase.Sqlite;
//import com.example.doit.MainActivity.ConnectingJinDuHan;

public class constant
{public static String nowBrd="nobrd";
 public static String yongHu="doit";
 public static String zhuJiUrl="";
 public static String tomcatPort="8011";
 public static String baoDaoUrl="/after/BaoDao";
 public static String jiaoZuoYeUrl="/after/JiaoZuoYe";
 public static String jiaoZuoYeDiZhi="http://"+zhuJiUrl+":"
		 +tomcatPort+
		 jiaoZuoYeUrl;
 public static String baoDaoDiZhi="http://"+zhuJiUrl+":"
		 +tomcatPort+
		 baoDaoUrl;
 public static String dataBaseName="Name.db";
 public static SQLiteDatabase sqliteDatabase;
 public static Sqlite sqlite;
 public static String psw="";

 public static double dengLuJinDu=0;
 public static boolean serviceOneDie=false;
 public static boolean serviceTwoDie=false;
 public static int service_One_LifeLight=0;
 public static int service_Two_LifeLight=0;
 public static ArrayList<String> checkedNumAry=new ArrayList<String>();
//public static ConnectingJinDuHan handler;
public static int waitLong=200;
public static String[] userList={"23125","12392","99987",
	"97583","10243","19982","67654","88654","67865","87659","12465",
	"21341","22110","23256","98652"};

public static ContentResolver contentResolver=null;
public static String receivePhoneNum="1232323";//the cell phone num to receive the msg

public static int testReadTimes=0;
public static int cursorCreated=0;
public static String[] tokenToBeReplace={"?", " " , "=" , "¡¾" , "¡¿" , "(" ,")" ,
	"?", "\\" ,"//" ,"*" };
public static String[] minGanCi={"","","",""};//the key word that shall be checked in msg


}
