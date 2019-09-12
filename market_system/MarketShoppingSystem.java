package market_system;

import java.util.ArrayList;
import java.util.Scanner;

//v2.0
public class MarketShoppingSystem {
    private ArrayList<GoodPurchase> purchaseList; /*= new ArrayList<>();*/

    public MarketShoppingSystem() {
        this.purchaseList = new ArrayList<>();
    }

    public MarketShoppingSystem(ArrayList<GoodPurchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public void welcome() {
        Scanner sc = new Scanner(System.in);
        String choose = " ";
        boolean isWelcome = true;//接收exitSystem()返回值;
        boolean isPurchase = true;//接收purchase()返回值;


        System.out.println("\t\t欢迎使用超市购物系统");

        //以exitSystem()的返回值作为终止条件;
        while (isWelcome) {

            //检验选择的选项是否有效;
            while (true) {
                System.out.println("请输入你要进行的操作:");
                System.out.println("1:购买商品\t2:结算并打印小票\t3:退出系统");
                choose = sc.nextLine();
                if (choose.equals("1") || choose.equals("2") || choose.equals("3")) {
                    break;
                }
                System.out.println("无此操作选项,请重新选择");
            }

            //选项有效,则调用不同选项的方法;
            switch (choose) {
                case "1":
                    purchaseInterface();//购买界面
                    while (isPurchase) {//输入购买项的循环,以purchase()返回值作为终止条件
                        isPurchase = purchase();
                    }
                    isPurchase = true;//结束循环后重置
                    break;
                case "2":
                    payment();
                    break;
                case "3":
                    isWelcome = exitSystem();
                    break;
                default:
                    System.out.println("因未知原因选项输入错误");
            }
        }

    }

    private void purchaseInterface(){
        System.out.println("-------------------------------");
        System.out.println("\t\t商品列表");
        System.out.println("商品id\t名称\t\t单价   计价单位");

        int size = purchaseList.size();
        for (int i = 0; i < size; i++) {
            purchaseList.get(i).printInfo();
        }

        System.out.println("-------------------------------");

        System.out.println("请输入您要购买的商品项(输入格式:商品id-购买数量),输入end表示购买结束");
    }

    private boolean purchase() {
        Scanner sc = new Scanner(System.in);
        String input = " ";//接收输入的购买项;
        String idInput = " ";
        int amoutInput = 0;

        int lenInput = 0;
        GoodPurchase g;
        boolean flag = false;
        String temp;//用于存储临时的字符串;
        int idIndex = 0;//存储id代表的对象所在位置的索引

        //循环判断输入合法性;
        while(true){
            input = sc.nextLine();
            lenInput = input.length();

            if(input.equals("end")){
                System.out.println("购买结束");
                return false;
            }

            //输入长度大于4;
            if(lenInput > 4){
                idInput = input.substring(0,3);//输入的id
                //进行id合法性检查;
                for (int i = 0; i < purchaseList.size(); i++) {
                    g = purchaseList.get(i);
                    if(g.getId().equals(idInput)){
                        idIndex = i;
                        flag = true;
                        break;
                    }
                }
                if(flag){//如果id合法,进行数量的合法性检查;
                    temp = input.substring(4);
                    for (int i = 0; i < temp.length(); i++) {//逐个检查字符是否为0~9数字,暂不考虑购买上限;
                        if(temp.charAt(i) < 48 || temp.charAt(i) > 57){
                            flag = false;
                            break;
                        }
                    }
                    if(flag){//通过检查,终止while循环;
                        amoutInput = Integer.parseInt(temp);
                        break;//跳出while循环
                    }else{
                        System.out.println("输入数量有误,请重新输入");
                    }

                }else{
                    System.out.println("输入id有误,无此商品,请重新输入");
                }
            }else{
                System.out.println("你输入的购买姿势不对,请换个姿势再来一次(格式:商品id-购买数量)");
            }

        }

        //输入的字符串合法,将其作为购买数据存入purchaseList;
        g = purchaseList.get(idIndex);
        g.setAmount(g.getAmount()+amoutInput);

        return true;
    }

    private void payment() {
        System.out.println("-------------------------------");
        System.out.println("\t\t欢迎光临");
        System.out.println("名称\t\t售价    数量    金额");
        System.out.println("-------------------------------");

        int amoutAll = 0;
        double paymentAll = 0;
        int itemNum = 0;
        int size = purchaseList.size();
        GoodPurchase g;
        for (int i = 0; i < size; i++) {
            g = purchaseList.get(i);
            if(g.getAmount() != 0){
                g.printPayment();
                amoutAll += g.getAmount();
                paymentAll += g.getPayment();
                itemNum++;
                //参与结算后清空该项购买数量;
                g.setAmount(0);
            }
        }
        System.out.println("-------------------------------");
        System.out.println(itemNum+"项商品");
        System.out.println("共计:"+amoutAll+"件");
        System.out.println("共:"+paymentAll+"元");
        System.out.println("-------------------------------");

    }

    private boolean exitSystem() {
        /*写入终止前进行的操作*/
        System.out.println("感谢您使用超市购物系统,欢迎下次光临,拜拜");
        return false;
    }

}
