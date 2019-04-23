package com.example.administrator.dqd;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeSoccerActivity extends Activity {

    private ImageView title_back, content_pic;
    private TextView content_writer, content_title, content_content;
    Button button_agree, button_share;
    int position;
    String[] title = {"恒大vs山东：恒大单外援塔利斯卡首发，费莱尼先发登场","天海vs上港：吴伟、林创益分别首发，孙可替补待命","ESPN记者：今夏巴萨中国行可能与恒大交手",
   "本周赛事看点：曼城复仇热刺？国米倒戈罗马？","伊涅斯塔：对阵巴萨是一次前所未有的体验，一定会很刺激","纳尔多：努力为西班牙人争取最好的联赛排名" };
    String[] writer = {"普通文青","EM","NNloveTT","英超臭皮匠","ForzaBan","皇家西班牙人俱乐部"};
    int[] pictureId = {R.drawable.viewpager0, R.drawable.viewpager1, R.drawable.viewpager2,
            R.drawable.viewpager3, R.drawable.viewpager4, R.drawable.viewpager5};
    String[] content = {"北京时间4月19日19:35，中超第6轮迎来一场焦点战，广州恒大淘宝将坐镇主场迎战山东鲁能泰山。赛前公布首发名单。\n" +
            "\n" +
            "广州恒大淘宝首发：32-刘殿座、35-李学鹏、21-高准翼、6-冯潇霆、5-张琳芃、10-郑智、16-黄博文、17-杨立瑜(U23)、9-塔利斯卡、11-张修维(U23)、29-郜林\n" +
            "\n" +
            "替补：1-刘世博(U23)、2-刘奕鸣、4-布朗宁、13-何超、15-严鼎皓(U23)、20-于汉超、25-邓涵文\n" +
            "\n" +
            "山东鲁能泰山首发：14-王大雷、5-郑铮、4-吉尔、35-戴琳、6-王彤、17-吴兴涵、22-蒿俊闵、25-费莱尼、21-刘彬彬、32-田鑫(U23)、9-佩莱\n" +
            "\n" +
            "替补：20-韩镕泽、40-李冠希(U23)、11-刘洋、13-张弛、16-李海龙(U23)、18-周海滨、28-陈科睿(U23)","北京时间4月19日19:35，中超第6轮的一场比赛，上海上港客场挑战天津天海。赛前，双方公布首发阵容。\n" +
            "\n" +
            "天津天海首发：1-张鹭、18-张诚、21-权敬原、25-糜昊伦、2-吴伟(U23)、6-裴帅、11-雷纳迪尼奥、17-张晓彬、39-王永珀、7-阿兰、9-杨旭\n" +
            "\n" +
            "替补：5-张成林、8-姚均晟、15-温家宝(U23)、16-郑达伦、20-张源(U23)、22-方镜淇、38-孙可\n" +
            "\n" +
            "上海上港首发：1-颜骏凌、6-蔡慧康、21-于海、23-傅欢、28-贺惯、15-林创益、37-陈彬彬(U23)、8-奥斯卡、11-吕文君、9-埃尔克森、10-胡尔克\n" +
            "\n" +
            "替补：2-张卫、4-王燊超、13-魏震(U23)、14-李圣龙、18-张一、24-雷文杰(U23)、34-陈威(U23)","据ESPN驻巴塞罗那记者透露，巴萨可能会在今夏的中国行中与广州恒大交手。\n" +
            "\n" +
            "这名记者(Moises Llorens)在推特上写道：“巴萨将会在7月23日在日本对阵切尔西，7月27日对阵神户胜利船。他们的中国行将会被宣布，其中保利尼奥所在的广州恒大淘宝是可能的对手之一。”\n" +
            "\n" +
            "恒大与巴萨曾经在2015年世俱杯半决赛中相遇过，当时巴萨3-0取胜，苏亚雷斯上演帽子戏法。\n" +
            "\n" +
            "巴萨在昨天还官宣了今夏在日本的“乐天杯”热身赛赛程，详细赛程如下：\n" +
            "\n" +
            "7月23日 巴塞罗那vs切尔西\n" +
            "\n" +
            "比赛地点：东京\n" +
            "\n" +
            "7月27日 神户胜利船vs巴塞罗那\n" +
            "\n" +
            "比赛地点：神户\n" +
            "\n" +
            "相关阅读\n" +
            "【官方：巴萨今夏在日本踢热身赛，约战神户胜利船和切尔西】\n" +
            "\n" +
            "【政府官网透露巴萨中国行日程：8月3日将到访苏州奥体中心】","欧冠1/4决赛尘埃落定。没想到，周中的受害者们纷纷又迎来恶战，到底是重振旗鼓，还是继续颓势，我们看看本周末的联赛重头戏。\n" +
            "\n" +
            "01 曼城VS 热刺\n" +
            "\n" +
            "看点：放学以后，老地方，你别跑\n" +
            "\n" +
            "4月20 （周六） 19:30\n" +
            "\n" +
            "就在昨天凌晨，热刺刚刚在曼彻斯特淘汰了曼城。\n" +
            "\n" +
            "明天，他们就将在同一块场地——曼城的伊蒂哈德体育场，再次一决高下。两场比赛，中间只隔着70小时，我查了一下两地之间的航班，光天上飞的时间，单程就要1个多小时，就算包机，来回至少也要4个小时左右。\n" +
            "\n" +
            "也就是说，热刺不吃不喝不睡觉，也只有66个小时，去准备这场比赛。当然，也可能波切蒂诺偷了个懒，两场比赛的准备内容是一模一样的。这不由让我想起小时候下课和同学闹别扭，最后的说辞总是，下课别跑，我们继续单挑。\n" +
            "\n" +
            "曼城在欧冠被热刺淘汰，如果只拿个足总杯冠军，球迷绝对不会答应。虽然不情不愿，但瓜迪奥拉的第三年，的确只有英超这一个目标了。\n" +
            "\n" +
            "球队中后场的问题，已经路人皆知。什么是打曼城最有效的方式？以前，莱斯特城说，是长传反击；现在，热刺和利物浦认为是高位逼抢和快速冲击。\n" +
            "\n" +
            "随着费尔南迪尼奥的老化和伤病，本就略显单薄的曼城后腰，只有运动能力不足的京多安在打主力。就他，还大概率将在今夏离队。热刺很可能继续在曼城由守转攻的软肋发起阻击。我们不知道，瓜迪奥拉将通过前场回撤还是边后卫内收解决后腰出球问题。我们也不知道，他会否增派一名防守队员帮助保护禁区前沿和加强回追。唯一可以肯定的是：拉波特肯定不会在周末的比赛中首发了。\n" +
            "\n" +
            "英超后面的Big 6内战越来越少，看一场，少一场，黄金时间的关键比赛，还带复仇属性，到底是瓜秃报仇趁早，还是波胖再次笑傲，这样的大戏，千万不要错过。\n" +
            "\n" +
            "02 尤文图斯VS 佛罗伦萨\n" +
            "\n" +
            "看点：出气之旅？继续憋着？\n" +
            "\n" +
            "4月21 （周日） 00：00\n" +
            "\n" +
            "用一个字概括尤文图斯，你能想到的是什么？没错，就是\"稳\"。\n" +
            "\n" +
            "但再稳的球队，在漫长的赛季中总有低谷，强队和一流球队之间的差别，就是怎么控制低谷期的成绩。客观地说，阿莱格里已经做得比较出色。\n" +
            "\n" +
            "球队最近的减员十分多，我本来以为阿莱格里又开始玩诈伤大法搞烟雾弹，戏弄阿贾克斯一把。直到比赛大名单出来才知道，他其实是个老实人。基耶利尼、夸德拉多、曼祖基奇都有伤在身，科斯塔等人也身体不适，本来兵多将广的尤文图斯在一个赛季最关键的时刻，输给了伤病。哦，也不全是伤病。\n" +
            "\n" +
            "坎塞洛在关键战役中，多次被放在替补席上，事后诸葛亮一下，尤文的进攻活力不足，或许与主教练的保守也有关系。\n" +
            "\n" +
            "周中的一身火气，想在佛罗伦萨身上泄愤。恐怕并不容易。\n" +
            "\n" +
            "佛罗伦萨最近完成了换帅，功勋蒙特拉回归球队。\n" +
            "\n" +
            "他们最近几年天天在过年，不过是王小二过年，每况愈下。德拉瓦莱兄弟在被足协伤透了心后，投入逐年减少，大家去各大论坛逛逛就知道，豆豆鞋的老板，现在仿佛与球队死忠们有杀父夺妻之恨。\n" +
            "\n" +
            "德拉瓦莱兄弟：我们不玩足球了，做做鞋子蛮好的。\n" +
            "\n" +
            "除了投入不行，球队的教练也不太行。虽然换帅频率赶不上他们家TOD'S牌鞋子上新频率。但3年半时间里，3个主帅，也算得上勤快。\n" +
            "\n" +
            "无论索萨还是保守顽固的皮奥利都未能带领佛罗伦萨重现辉煌，球队沉闷的打法也让球迷看不到希望。本赛季佛罗伦萨堪称平局大师，他们在32场意甲里，收获了一半的平局，简直赌狗福音。虽然坐拥名宿之子双枪——小基耶萨和小西蒙尼，但球队只收获了46个联赛进球。\n" +
            "\n" +
            "4月10日，球队终于忍无可忍，开掉了只会边路传中的皮奥利，迎来2012-2015年成绩高峰的带队老熟人蒙特拉。蒙特拉在位时，没有赶上好时光，意甲只有3个欧冠名额，所以当时连年排名第四的佛罗伦萨并不高调。但蒙特拉一走，他们就马上掉到中游，今年更是被\"水手\"和\"公牛\"超越，目前排名第10，分差较大，无欲无求。\n" +
            "\n" +
            "只会传中大法的皮奥利已经走人\n" +
            "\n" +
            "此番尤文碰到换帅的佛罗伦萨，结果如何还真不好说。一方面，两队是意甲众所周知的死敌，恶劣的关系，可能连国际米兰和尤文都不如；另一方面，紫花刚刚换帅，重建考核期，球员都会更加卖力。\n" +
            "\n" +
            "03 国际米兰VS 罗马\n" +
            "\n" +
            "看点：球场上没有革命友谊\n" +
            "\n" +
            "4月21 （周日） 02：30\n" +
            "\n" +
            "又到了每周一次的阿苏喷斯帕莱蒂祖孙三代时间。\n" +
            "\n" +
            "不过在此之前，我们可以先看看两队的渊源。\n" +
            "\n" +
            "新世纪，两队都算是反尤文阵营的铁杆。在神圣同盟时期，罗马就和国际米兰穿一条裤子。从来就没听说过两队球迷之间有什么大仇。不过随着两队的每况愈下，争四（进欧冠）日趋激烈，以及两队主席的更替，之间的关系逐渐变得微妙。\n" +
            "\n" +
            "无论在转会市场，还是球场，都屡屡擦出火花。之前为希克，去年为马尔科姆，都吵得不可开交。搞笑的是，最后马尔科姆去了巴萨。\n" +
            "\n" +
            "两队还有不少相互之间的老熟人，比如本来打算来国米技术扶贫、却发现米兰夜店十分好玩的斯帕莱蒂\"爸爸\"——纳英戈兰，国际米兰半卖半送却在罗马打出身价的扎尼奥洛，以及国际米兰曾经的防线未来小胡安和桑顿。\n" +
            "\n" +
            "互相知根知底的两队，可谓棋逢对手。事实上，他们在争四的旅途中也十分\"谦让\"——只要国际米兰当轮输球，罗马保证一定不拿分。\n" +
            "\n" +
            "目前国际米兰在积分榜上领先第五的罗马6分，不过考虑到后面还要和老大、老二——尤文和那不勒斯交手，这6分的优势并不稳当。留给斯帕莱蒂一家的时间不多了。\n" +
            "\n" +
            "本周焦点赛事还有状态糟糕的豪门巴黎对战回升的摩纳哥，以及每集必看的武磊之西班牙人大战莱万特，特别是西班牙人的比赛，时间很好，极其少见地被放在了周日黄金档晚6点，国内球迷不可错过。","在巴萨官方宣布将在今夏约战小白目前效力的神户胜利船后，这位巴萨传奇中场表示，自己很期待与老东家的对决。\n" +
            "\n" +
            "伊涅斯塔在发布会上谈到这个话题时说道：“与巴萨的对决会非常刺激，我此前效力那么多年的球队能够专程来到日本与我现在的球队对阵，这感觉非常棒。我希望这会是一场有趣的比赛”。\n" +
            "\n" +
            "“(对阵巴萨)对我来说是一次前所未有的体验，我也很期待与以前的队友们重逢。我们的胜算？足球世界里一切皆有可能，但我们也不会很看重这场比赛的胜负。你要面对的是远超于自身实力的对手，因此这是一个很好的成长机会，同时也是球迷们的节日”。","大卫-洛佩斯赛季报销后，纳尔多搭档埃尔莫索成为西班牙人后防线上稳定的组合。这名巴西中后卫的优异表现，让人有理由相信，球队可以继续信赖现在这条后防线。纳尔多表示，“本周开始我们在训练中明确了思想，那就是力争把现在的战绩保持到赛季结束”。\n" +
            "\n" +
            "他同时还提醒说，下一场比赛对于对手莱万特来说“很关键”，“因为他们需要胜利，而这是一支不简单的队伍，他们总是顽强拼搏”。纳尔多强调了所有球员“继续保持优异战绩”的意愿。他说，“我们有一个良好的开始，现在我们希望保持到赛季结束”。\n" +
            "\n" +
            "纳尔多认为整支球队已经统一了思想，那就是“努力争取最好的联赛排名，一切皆有可能，我们将努力实现这个目标”。巴西后卫表示最近几轮比赛自己感觉“非常满意”，同时他也期待“将优异的战绩保持到赛季结束”。"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_soccer);

        Bundle bundle = this.getIntent().getExtras(); //读取intent的数据给bundle对象
        position = bundle.getInt("position");//通过key得到value

        //设置状态栏
        status();

        initView();

        initData();

        initListen();


    }

    private void initView() {
        title_back = findViewById(R.id.title_back);
        content_writer = findViewById(R.id.content_writer);
        content_title = findViewById(R.id.content_title);
        content_content = findViewById(R.id.content);
        content_pic = findViewById(R.id.content_pic);
        button_agree = findViewById(R.id.content_agree);
        button_share = findViewById(R.id.content_share);
    }

    private void initData() {
        if((position%6)>0){
            content_writer.setText(writer[(position%6)-1]);
            content_title.setText(title[(position%6)-1]);
            content_pic.setImageResource(pictureId[(position%6)-1]);
            content_content.setText(content[(position%6)-1]);
        }else{
            content_writer.setText(writer[5]);
            content_title.setText(title[5]);
            content_pic.setImageResource(pictureId[5]);
            content_content.setText(content[5]);
        }



    }

    private void initListen() {
        //返回键点击事件
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //分享点击事件
        button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_share.getText()=="21分享"){
                    Toast.makeText(HomeSoccerActivity.this, "已分享过了", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(HomeSoccerActivity.this, "已分享给好友", Toast.LENGTH_SHORT).show();
                    button_share.setText("21分享");
                }
            }
        });

        //分享点击事件
        button_agree.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(button_agree.getText()=="172赞"){
                    Toast.makeText(HomeSoccerActivity.this, "取消点赞", Toast.LENGTH_SHORT).show();
                    button_agree.setText("171赞");
                }else{
                    Toast.makeText(HomeSoccerActivity.this, "已点赞", Toast.LENGTH_SHORT).show();
                    button_agree.setText("172赞");
                }

            }
        });
    }

    //设置状态栏
    private void status() {
        //状态栏设置
        Window window = HomeSoccerActivity.this.getWindow();
        //设置透明状态栏，这样才能让ContentView向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //设置flag调用setStatusBarColor来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        //window.setStatusBarColor(R.color.green);

        ViewGroup mContentView = HomeSoccerActivity.this.findViewById(Window.ID_ANDROID_CONTENT);

        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {

            //!!!是设置ContentView的第一个子view的FitsSystemWindow,使其不为系统View预留空间
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }
}
