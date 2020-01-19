package com.love.jax.activity.design.mvvm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.love.jax.BR;
import com.love.jax.R;



import java.util.ArrayList;
import java.util.List;

/**
 *  todo:MVVM模式设置显示listView
 *  正常设置setContentView、findViewById--->ListView、XML中布局正常设置
 *  创建Adapter，variableId 表示 BR.food
 *  每个item中设置layout，进行数据与视图关联
 *
 *  对于过大的项目，数据绑定需要过大的内存
 *  ActivityMvvmObjectBinding中给每个控件赋值
 */
public class MvvmListActivity extends AppCompatActivity {

    ListView listView;
    List<Food> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm_list);
        listView= (ListView) findViewById(R.id.list);
        initData();
    }

    private void initData() {
        foods= new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(json);
            JSONArray tngou = jo.getJSONArray("tngou");
            for (int i = 0; i < 7; i++) {
                JSONObject item = tngou.getJSONObject(i);
                String description = item.getString("description");
//                String img = "http://tnfs.tngou.net/image"+item.getString("img");
                String img = imageThumbUrls[i];
                String keywords = "【关键词】 "+item.getString("keywords");
                String summary = item.getString("summary");
                foods.add(new Food(img,description, keywords));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("jax","----->     "+foods.size());
        ComonAdapter<Food> comonAdapter=new ComonAdapter<>(this,getLayoutInflater()
                ,R.layout.item_mvvm_layout, BR.food,foods);
        listView.setAdapter(comonAdapter);
    }


    public final static String[] imageThumbUrls =new String[] {
            "http://b.zol-img.com.cn/desk/bizhi/image/3/960x600/1375841395686.jpg",
            "http://cdn.duitang.com/uploads/item/201311/03/20131103171224_rr2aL.jpeg",
            "http://h.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=429e7b1b92ef76c6d087f32fa826d1cc/7acb0a46f21fbe09cc206a2e69600c338744ad8a.jpg",
            "http://pica.nipic.com/2007-12-21/2007122115114908_2.jpg",
            "http://cdn.duitang.com/uploads/item/201405/13/20140513212305_XcKLG.jpeg",
            "http://photo.loveyd.com/uploads/allimg/080618/1110324.jpg",
            "http://pic.dbw.cn/0/01/33/59/1335968_847719.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/a8773912b31bb051a862339c337adab44bede0c4.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0feeea8a30f5e6034a85edf720f.jpg",
            "http://img0.pconline.com.cn/pconline/bizi/desktop/1412/ER2.jpg",
            "http://img02.tooopen.com/images/20140320/sy_57121781945.jpg"
    };


    private  String json="{\n" +
            "    \"status\": true,\n" +
            "    \"total\": 170,\n" +
            "    \"tngou\": [\n" +
            "        {\n" +
            "            \"count\": 8570,\n" +
            "            \"description\": \"鹌鹑蛋对于肺病，肝炎，脑膜炎，胃病，糖尿病，哮喘，心脏病，神经衰弱，高血压，低血压，动脉硬化，小儿疳积等病症均有较好的辅助疗效\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 4,\n" +
            "            \"img\": \"/food/150804/4903e1e66e312a862a81682ce80884c9.jpg\",\n" +
            "            \"keywords\": \"鹌鹑蛋 鸡蛋 月经不调 营养不良 神经衰弱 \",\n" +
            "            \"name\": \"鹌鹑蛋\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>1.鹌鹑蛋味甘，性平。</p> \\n<p>2.有补益气血、强身健脑、丰肌泽肤等功效。</p> \\n<p>3.鹌鹑蛋对贫血、营养不良、神经衰弱、月经不调、高血压、支气管炎、血管硬化等病人具有调补作用。</p> \\n<p>4.对有贫血、月经不调的女性，其调补、养颜、美肤功用尤为显著。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 6779,\n" +
            "            \"description\": \"白菜耐储存，过去，很多地方白菜是他们整个冬季唯一可吃的蔬菜，一户人家往往需要储存数百斤白菜以应付过冬，有“冬日白菜美如笋”之称，现在也是我国居民餐桌上必不可少的一道美蔬\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 7,\n" +
            "            \"img\": \"/food/150804/86075bf97305f125c87d41c9d744d3c7.jpg\",\n" +
            "            \"keywords\": \"白菜 大白菜 维生素 纤维素 蛋白质 \",\n" +
            "            \"name\": \"白菜\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>1.白菜微寒、味甘、性平，归肠、胃经。</p> \\n<p>2.有解热除烦、通利肠胃、养胃生津、除烦解渴、利尿通便、清热解毒。</p> \\n<p>3.可用于肺热咳嗽、便秘、丹毒、漆疮。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 4054,\n" +
            "            \"description\": \"在体内还能促进和增强单核细胞系统和吞噬功能，提高机体的体液免疫能力，因此百合对多种癌症均有较好的防冶效果\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 22,\n" +
            "            \"img\": \"/food/150804/3780244482481ab0906eb10912aba215.jpg\",\n" +
            "            \"keywords\": \"百合 作用 多种 咳嗽 生物碱 \",\n" +
            "            \"name\": \"百合\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>1.百合，味甘微苦，性平，入心肺经，在药用上有润肺止咳、养阴消热、清心安神之效。</p> \\n<p>2.单味百合煎服或与其它食物、药物一并混食均能发挥作用。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 2925,\n" +
            "            \"description\": \"营养价值增强机体免疫功能：萝卜含丰富的维生素C和微量元素锌，有助于增强机体的免疫功能，提高抗病能力\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 36,\n" +
            "            \"img\": \"/food/150804/71166bd7cf2dc866d5ea5890c0847d26.jpg\",\n" +
            "            \"keywords\": \"萝卜 帮助 白萝卜 消化 提高 \",\n" +
            "            \"name\": \"白萝卜\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>1.萝卜味甘、辛、性凉，入肺、胃、肺、大肠经。</p> \\n<p>2.可清热生津、凉血止血、下气宽中、消食化滞、开胃健脾、顺气化痰。</p> \\n<p>3.主要用于腹胀停食、腹痛、咳嗽、痰多等症。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 1542,\n" +
            "            \"description\": \"板栗的食疗价值营养价值：富含铜，铜是人体健康不可缺少的微量营养素，对于血液、中枢神经和免疫系统，头发、皮肤和骨骼组织以及脑子和肝、心等内脏的发育和功能有重要影响\",\n" +
            "            \"disease\": \"虚寒泄泻,脾虚泄泻,冠状动脉粥样硬化性心脏病,老年人骨质疏松,老年人高血压,闭塞性动脉硬化,腹泻,小儿高血压,动脉硬化性闭塞症,动脉硬化,高血压,骨质疏松,慢性腹泻\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"榛子,南瓜(栗面）,板栗\",\n" +
            "            \"id\": 73,\n" +
            "            \"img\": \"/food/150804/9aa0758ec7415c00cd606053530a434f.jpg\",\n" +
            "            \"keywords\": \"板栗 适宜 人群 食用 体质 \",\n" +
            "            \"name\": \"板栗\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \"<p>【性质】温</p> \\n<p>【五味】甘</p> \\n<p>【热量】212.00大卡(千焦)/100克 </p> \\n<p>【功效】祛脂降压,止泄,提高免疫力,疗头痛头晕,补充能量,健脾,壮骨</p> \\n<p>【板栗是什么】 栗子，又名板栗，不仅含有大量淀粉，而且含有蛋白质、脂肪、B族维生素等多种营养成分，素有“干果之王”的美称。栗子可代粮，与枣、柿子并称为“铁杆庄稼”、“木本粮食”，是一种价廉物美、富有营养的滋补品及补养良药。</p>\",\n" +
            "            \"symptom\": \"神疲乏力,心悸怔忡,持续性头晕,顽固性口腔溃疡,间歇性头晕,情绪性高血压,口舌生疮,活动后气促,头昏眼花,头晕,无痛性口腔溃疡,劳累后心悸,皮肤黏膜苍白,眼花,乏力,口腔溃疡,骨质疏松,高血压,心悸,耳鸣\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 1278,\n" +
            "            \"description\": \"包心菜中含有某种溃疡愈合因子，对溃疡有着很好的治疗作用，能加速创面愈合，是胃溃疡患者的有效食品\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 77,\n" +
            "            \"img\": \"/food/150804/442e5741e0ffdeca3b90e017b806e088.jpg\",\n" +
            "            \"keywords\": \"包心菜 患者 卷心菜 中含有 作用 \",\n" +
            "            \"name\": \"包心菜\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \"\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 2762,\n" +
            "            \"description\": \"菠菜中所含的胡萝卜素，在人体内转变成维生素A，能维护正常视力和上皮细胞的健康，增加预防传染病的能力，促进儿童生长发育\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 102,\n" +
            "            \"img\": \"/food/150804/5c8090e3fbdaf03174c45c9f4ef161ba.jpg\",\n" +
            "            \"keywords\": \"菠菜 食用 促进 维生素 胡萝卜素 \",\n" +
            "            \"name\": \"菠菜\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>1.菠菜味甘、性凉，入大肠、胃经。</p> \\n<p>2.可补血止血，利五脏，通肠胃，调中气，活血脉，止渴润肠，敛阴润燥，滋阴平肝，助消化。</p> \\n<p>3.主治高血压、头痛、目眩、风火赤眼、糖尿病、便秘、消化不良、跌打损伤、衄血、便血、坏血病、大便涩滞。</p> \\n<p>4.近来用以治贫血。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 818,\n" +
            "            \"description\": \"食材介绍英文名：Mint，为唇形科植物，“薄荷”即同属其他干燥全草，多生于山野湿地河旁，根茎横生地下\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 103,\n" +
            "            \"img\": \"/food/150804/d291a7a5207e1a8d61bcd17ff4e5142c.jpg\",\n" +
            "            \"keywords\": \"月见草 先端 白色 花期 基部 \",\n" +
            "            \"name\": \"薄荷\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>本品味甘，性温。清肝，明目，去翳，拔毒生肌，强筋壮骨、祛风除湿。</p> \\n<p>花、叶、果：用于急慢性结合膜炎，角膜炎，角膜翳；麻疹引起的结膜炎；鲜叶：外用治已溃疮疖脓肿，脚臁外伤糜烂。</p> \\n<p>全株青气芳香，清爽可口。平常以薄荷代茶，清心明目。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 1151,\n" +
            "            \"description\": \"食用效果菠萝味甘、微酸、性平，入胃、肾经；具有止渴解烦，健脾解渴，消肿，祛湿，醒酒益气的功效；可用于消化不良、肠炎腹泻、伤暑、身热烦渴等症，也可用于高血压眩晕、手足软弱无力的辅助治疗\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 104,\n" +
            "            \"img\": \"/food/150804/1bc2522d434968d3d057883c0a6812ee.jpg\",\n" +
            "            \"keywords\": \"菠萝 食用 之一 品种 高血压 \",\n" +
            "            \"name\": \"菠萝\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>菠萝味甘、微酸、性平，入胃、肾经；</p> \\n<p>具有止渴解烦，健脾解渴，消肿，祛湿，醒酒益气的功效；</p> \\n<p>可用于消化不良、肠炎腹泻、伤暑、身热烦渴等症，也可用于高血压眩晕、手足软弱无力的辅助治疗。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 470,\n" +
            "            \"description\": \"鲅鱼体长呈纺锤形，头长大于体高，口大而斜袭，背部蓝黑色，腹部银灰色，体侧有大小不等的黑色斑点，侧线呈不规则斑纹状，有两个背鳍相距甚小，背、臀鳍后各有8-9个小鳍，尾鳍分叉深\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 107,\n" +
            "            \"img\": \"/food/150804/f32c9dd1130a9f696926ceefa7814f24.jpg\",\n" +
            "            \"keywords\": \"鲅鱼 神经衰弱 营养不良 有 虚弱 \",\n" +
            "            \"name\": \"鲅鱼\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>中医认为，鲅鱼有补气、平咳作用，对体弱咳喘有一定疗效。</p> \\n<p>鲅鱼还具有提神和防衰老等食疗功能，常食对治疗贫血、早衰、营养不良、产后虚弱和神经衰弱等症会有一定辅助疗效。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 889,\n" +
            "            \"description\": \"英国在对荸荠的研究中发现一种“荸荠英”，这种物质对黄金色葡萄球菌、大肠杆菌、产气杆菌及绿脓杆菌均有一定的抑制作用，对降低血压也有一定效果\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 109,\n" +
            "            \"img\": \"/food/150804/90be8753a3d963d0bbc85a2afd26f2a6.jpg\",\n" +
            "            \"keywords\": \"荸荠 一定 有 尿路感染 食用 \",\n" +
            "            \"name\": \"荸荠\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>1.荸荠甘、寒，能清肺热，又富含黏液质，有生津润肺、化痰利肠、通淋利尿、消痈解毒、凉血化湿、消食除胀。</p> \\n<p>2.主治热病消渴、黄疸、目赤、咽喉肿痛、小便赤热短少、外感风热、痞积等病症。</p> \\n<p>3.荸荠具有清热泻火的良好功效，应用于肺热咳嗽，痰浓难咳：荸荠汁1杯，川贝1.5克(研成粉)，拌匀服，每天2—3次。既可清热生津，又可补充营养，最宜用于发烧病人。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 975,\n" +
            "            \"description\": \"草莓外观呈浆果状圆体或心形，鲜美红嫩，果肉多汁，酸甜可口，香味浓郁，是水果中难得的色、香、味俱佳者，因此常被人们誉为果中皇后\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 122,\n" +
            "            \"img\": \"/food/150804/9c19a90629e423bd3394fd8d7d85effe.jpg\",\n" +
            "            \"keywords\": \"草莓 丰富 营养价值 中含有 食用 \",\n" +
            "            \"name\": \"草莓\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>1.草莓味甘、酸，性凉，无毒。</p> \\n<p>2.具有润肺生津、清热凉血、健脾解酒等功效。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 908,\n" +
            "            \"description\": \"白米经过精加工失去了本来就不多的营养成分，而糙米集中了各种谷物的营养，对人体有极大的营养价值\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 124,\n" +
            "            \"img\": \"/food/150804/e3063dc24b5940523182ac03f4500565.jpg\",\n" +
            "            \"keywords\": \"糙米 食用 血液循环 营养价值 维生素 \",\n" +
            "            \"name\": \"糙米\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>用糙米做的米饭，虽然口感稍硬一些，但吃起来别有一番风味。</p> \\n<p>它不但能够充饥果腹，也可制成谷片，通常是搭配牛奶一起当早餐食用。</p> \\n<p>近年来，日本、新加坡、菲律宾、泰国等国家，掀起了食用糙米食品的热潮。</p> \\n<p>在国内一些大中城市，很多消费者也把吃糙米食品视为时尚。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 408,\n" +
            "            \"description\": \"蛏生活在浅海泥沙中,有左右相等的两个贝壳,壳质脆薄,呈长方形,表面常生长一层浅绿色的薄皮\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 144,\n" +
            "            \"img\": \"/food/150804/79801b7b6c9893ef0dae8d93446bde8b.jpg\",\n" +
            "            \"keywords\": \"营养价值 水肿 痢疾 味道鲜美 适用人群 \",\n" +
            "            \"name\": \"蛏子\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>1.蛏肉味甘咸、性寒，入心、肝、肾经。</p> \\n<p>2.具有补阴，清热，除烦，解酒毒等功效。</p> \\n<p>3.对产后虚损，烦热口渴，湿热水肿，痢疾，醉酒等有一定治疗作用。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 875,\n" +
            "            \"description\": \"营养价值橙子含有大量维生素C和胡萝卜素，可以抑制致癌物质的形成，还能软化和保护血管，促进血液循环，降低胆固醇和血脂；研究显示，每天喝3杯橙汁可以增加体内高密度脂蛋白\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 145,\n" +
            "            \"img\": \"/food/150804/524e07de9dfcfbac20409147404e953d.jpg\",\n" +
            "            \"keywords\": \"橙子 胡萝卜素 食用 维生素 脂蛋白 \",\n" +
            "            \"name\": \"橙子\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>橙性凉、味甘酸；</p> \\n<p>具有生津止渴，帮助消化，和胃止痛等功效。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 440,\n" +
            "            \"description\": \"带鱼肉嫩体肥、味道鲜美，只有中间一条大骨，食用起来方便，并且鲜食、腌制、冷冻都可以，常常成为人们餐桌上的美食\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 180,\n" +
            "            \"img\": \"/food/150804/27bb16c8e329134d33dc2ab5be9bc185.jpg\",\n" +
            "            \"keywords\": \"带鱼 心血管 食用 作用 脂肪酸 \",\n" +
            "            \"name\": \"带鱼\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>带鱼性温、味甘、咸；归肝、脾经。</p> \\n<p>有补脾、益气、暖胃、养肝、泽肤、补气、养血、健美的作用。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 1222,\n" +
            "            \"description\": \"食用效果性凉、味甘、咸，归脾、胃经；具有益气宽中、消渴除热的功效；大麦芽可消食宽中下气，对消化不良、饱闷腹胀有明显疗效；并且有回乳功效\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 183,\n" +
            "            \"img\": \"/food/150804/57507d5335bdc5552e5d607da8bffb6e.jpg\",\n" +
            "            \"keywords\": \"大麦芽 大麦 功效 消化不良 制造 \",\n" +
            "            \"name\": \"大麦\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>性凉、味甘、咸，归脾、胃经；</p> \\n<p>具有益气宽中、消渴除热的功效；</p> \\n<p>大麦芽可消食宽中下气，对消化不良、饱闷腹胀有明显疗效；并且有回乳功效。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 688,\n" +
            "            \"description\": \"其他说明党参原产于山西上党，所以称为党参，功效与人参类似，只是效力不如人参强，同样也是实症、热症的人不能用\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 189,\n" +
            "            \"img\": \"/food/150804/5c7e50b6fcb9ab950f7c23c75ca3b19d.jpg\",\n" +
            "            \"keywords\": \"党参 人参 功效 经常出现 排骨汤 \",\n" +
            "            \"name\": \"党参\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \"\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 949,\n" +
            "            \"description\": \"防止癌肿：大蒜素及其同系物能有效地抑制癌细胞活性，使之不能正常生长代谢，最终导致癌细胞死亡，并且大蒜素还能激活巨噬细胞的吞噬能力，增强人体免疫功能，预防癌症的发生；大蒜素能阻断亚硝酸盐致癌物质的合成而防治癌肿\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 201,\n" +
            "            \"img\": \"/food/150804/c702e4c05547fe3ca8ee016775559339.jpg\",\n" +
            "            \"keywords\": \"大蒜 预防 大蒜素 铅中毒 胰岛素 \",\n" +
            "            \"name\": \"大蒜\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \" \\n<p></p> \\n<p>1.大蒜味辛、性温，入脾、胃、肺经。</p> \\n<p>2.可温中消食、行滞气、暖脾胃、消积、解毒、杀虫。</p> \\n<p>3.主治饮食积滞、脘腹冷痛、水肿胀满、泄泻、痢疾、疟疾、百日咳、痈疽肿毒、白秃癣疮、蛇虫咬伤以及钩虫、蛲虫等病症。</p> \\n<p></p> \\n<p></p>\",\n" +
            "            \"symptom\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"count\": 1396,\n" +
            "            \"description\": \"大枣，又名红枣、干枣、枣子，起源于中国，在中国已有八千多年的种植历史，自古以来就被列为“五果”（栗、桃、李、杏、枣）之一\",\n" +
            "            \"disease\": \"\",\n" +
            "            \"fcount\": 0,\n" +
            "            \"food\": \"\",\n" +
            "            \"id\": 204,\n" +
            "            \"img\": \"/food/150804/51ad536adaeb95f33c8abbdd69565abd.jpg\",\n" +
            "            \"keywords\": \"维生素 自古以来 红枣 营养成分 胡萝卜素 \",\n" +
            "            \"name\": \"大枣\",\n" +
            "            \"rcount\": 0,\n" +
            "            \"summary\": \"\",\n" +
            "            \"symptom\": \"\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
}
