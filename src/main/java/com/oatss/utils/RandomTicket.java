package com.oatss.utils;
import com.oatss.entity.Ticket;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
class RandomChineseCountryRegion {
    private static final List<String> PROVINCE_LIST = new ArrayList<>(
            Arrays.asList("北京", "天津", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "上海", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "广西", "海南", "重庆", "四川", "贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆", "台湾", "香港", "澳门")
    );
    private static final List<List<String>> PROVINCE_CITY_LIST = new ArrayList<>(
            Arrays.asList(
                    Arrays.asList("东城区", "西城区", "朝阳区", "丰台区", "石景山区", "海淀区", "门头沟区", "房山区", "通州区", "顺义区", "昌平区", "大兴区", "平谷区", "怀柔区", "密云区", "延庆区"),
                    Arrays.asList("和平区", "河东区", "河西区", "南开区", "河北区", "红桥区", "东丽区", "西青区", "津南区", "北辰区", "武清区", "宝坻区", "滨海新区", "宁河区", "静海区", "蓟州区"),
                    Arrays.asList("石家庄", "唐山", "秦皇岛", "邯郸", "邢台", "保定", "张家口", "承德", "沧州", "廊坊", "衡水"),
                    Arrays.asList("太原", "大同", "阳泉", "长治", "晋城", "朔州", "晋中", "运城", "忻州", "临汾", "吕梁"),
                    Arrays.asList("呼和浩特", "包头", "乌海", "赤峰", "通辽", "鄂尔多斯", "呼伦贝尔", "巴彦淖尔", "乌兰察布", "兴安", "锡林郭勒", "阿拉善"),
                    Arrays.asList("沈阳", "大连", "鞍山", "抚顺", "本溪", "丹东", "锦州", "营口", "阜新", "辽阳", "盘锦", "铁岭", "朝阳", "葫芦岛"),
                    Arrays.asList("长春", "吉林", "四平", "辽源", "通化", "白山", "松原", "白城", "延边"),
                    Arrays.asList("哈尔滨", "齐齐哈尔", "鸡西", "鹤岗", "双鸭山", "大庆", "伊春", "佳木斯", "七台河", "牡丹江", "黑河", "绥化", "大兴安岭"),
                    Arrays.asList("黄浦区", "徐汇区", "长宁区", "静安区", "普陀区", "虹口区", "杨浦区", "闵行区", "宝山区", "嘉定区", "浦东新区", "金山区", "松江区", "青浦区", "奉贤区", "崇明区"),
                    Arrays.asList("南京", "无锡", "徐州", "常州", "苏州", "南通", "连云港", "淮安", "盐城", "扬州", "镇江", "泰州", "宿迁"),
                    Arrays.asList("杭州", "宁波", "温州", "嘉兴", "湖州", "绍兴", "金华", "衢州", "舟山", "台州", "丽水"),
                    Arrays.asList("合肥", "芜湖", "蚌埠", "淮南", "马鞍山", "淮北", "铜陵", "安庆", "黄山", "滁州", "阜阳", "宿州", "六安", "亳州", "池州", "宣城"),
                    Arrays.asList("福州", "厦门", "莆田", "三明", "泉州", "漳州", "南平", "龙岩", "宁德"),
                    Arrays.asList("南昌", "景德镇", "萍乡", "九江", "新余", "鹰潭", "赣州", "吉安", "宜春", "抚州", "上饶"),
                    Arrays.asList("济南", "青岛", "淄博", "枣庄", "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照", "临沂", "德州", "聊城", "滨州", "菏泽"),
                    Arrays.asList("郑州", "开封", "洛阳", "平顶山", "安阳", "鹤壁", "新乡", "焦作", "濮阳", "许昌", "漯河", "三门峡", "南阳", "商丘", "信阳", "周口", "驻马店", "济源"),
                    Arrays.asList("武汉", "黄石", "十堰", "宜昌", "襄阳", "鄂州", "荆门", "孝感", "荆州", "黄冈", "咸宁", "随州", "恩施", "仙桃", "潜江", "天门", "神农架"),
                    Arrays.asList("长沙", "株洲", "湘潭", "衡阳", "邵阳", "岳阳", "常德", "张家界", "益阳", "郴州", "永州", "怀化", "娄底", "湘西"),
                    Arrays.asList("广州", "韶关", "深圳", "珠海", "汕头", "佛山", "江门", "湛江", "茂名", "肇庆", "惠州", "梅州", "汕尾", "河源", "阳江", "清远", "东莞", "中山", "潮州", "揭阳", "云浮"),
                    Arrays.asList("南宁", "柳州", "桂林", "梧州", "北海", "防城港", "钦州", "贵港", "玉林", "百色", "贺州", "河池", "来宾", "崇左"),
                    Arrays.asList("海口", "三亚", "三沙", "儋州", "五指山", "琼海", "文昌", "万宁", "东方", "定安", "屯昌", "澄迈", "临高", "白沙", "昌江", "乐东", "陵水", "保亭", "琼中"),
                    Arrays.asList("万州区", "涪陵区", "渝中区", "大渡口区", "江北区", "沙坪坝区", "九龙坡区", "南岸区", "北碚区", "綦江区", "大足区", "渝北区", "巴南区", "黔江区", "长寿区", "江津区", "合川区", "永川区", "南川区", "璧山区", "铜梁区", "潼南区", "荣昌区", "开州区", "梁平区", "武隆区", "城口", "丰都", "垫江", "忠县", "云阳", "奉节", "巫山", "巫溪", "石柱", "秀山", "酉阳", "彭水"),
                    Arrays.asList("成都", "自贡", "攀枝花", "泸州", "德阳", "绵阳", "广元", "遂宁", "内江", "乐山", "南充", "眉山", "宜宾", "广安", "达州", "雅安", "巴中", "资阳", "阿坝", "甘孜", "凉山"),
                    Arrays.asList("贵阳", "六盘水", "遵义", "安顺", "毕节", "铜仁", "黔西南", "黔东南", "黔南"),
                    Arrays.asList("昆明", "曲靖", "玉溪", "保山", "昭通", "丽江", "普洱", "临沧", "楚雄", "红河", "文山", "西双版纳", "大理", "德宏", "怒江", "迪庆"),
                    Arrays.asList("拉萨", "日喀则", "昌都", "林芝", "山南", "那曲", "阿里"),
                    Arrays.asList("西安", "铜川", "宝鸡", "咸阳", "渭南", "延安", "汉中", "榆林", "安康", "商洛"),
                    Arrays.asList("兰州", "嘉峪关", "金昌", "白银", "天水", "武威", "张掖", "平凉", "酒泉", "庆阳", "定西", "陇南", "临夏", "甘南"),
                    Arrays.asList("西宁", "海东", "海北", "黄南", "海南", "果洛", "玉树", "海西"),
                    Arrays.asList("银川", "石嘴山", "吴忠", "固原", "中卫"),
                    Arrays.asList("乌鲁木齐", "克拉玛依", "吐鲁番", "哈密", "昌吉", "博尔塔拉", "巴音郭楞", "阿克苏", "克孜勒苏", "喀什", "和田", "伊犁", "塔城", "阿勒泰", "石河子", "阿拉尔", "图木舒克", "五家渠", "北屯", "铁门关", "双河", "可克达拉", "昆玉", "胡杨河"),
                    Arrays.asList("台北市", "高雄市", "基隆市", "台中市", "台南市", "新竹市", "嘉义市", "台北县", "宜兰县", "桃园县", "新竹县", "苗栗县", "台中县", "彰化县", "南投县", "云林县", "嘉义县", "台南县", "高雄县", "屏东县", "台东县", "花莲县", "澎湖县"),
                    Arrays.asList("中西区", "东区", "九龙城区", "观塘区", "南区", "深水埗区", "黄大仙区", "湾仔区", "油尖旺区", "离岛区", "葵青区", "北区", "西贡区", "沙田区", "屯门区", "大埔区", "荃湾区", "元朗区"),
                    Arrays.asList("花地玛堂区", "圣安多尼堂区", "大堂区", "望德堂区", "风顺堂区", "氹仔", "路环")
            )
    );
    private static final List<AbstractMap.SimpleEntry<String, List<String>>> CITY_LIST = new ArrayList<>();
    static {
        for (int i = 0; i < PROVINCE_LIST.size(); i++) {
            CITY_LIST.add(new AbstractMap.SimpleEntry<>(PROVINCE_LIST.get(i), PROVINCE_CITY_LIST.get(i)));
        }
    }
    private RandomChineseCountryRegion() {

    }
    private static void getRandomList(List<String> list) {
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < list.size(); i++) {
            Collections.swap(list, secureRandom.nextInt(0, list.size()), secureRandom.nextInt(0, list.size()));
        }
    }
    public static String getRandomChineseProvince() {
        List<String> provinceList = PROVINCE_LIST;
        getRandomList(provinceList);
        return provinceList.get(new SecureRandom().nextInt(0, provinceList.size()));
    }
    public static String getRandomChineseCity(String province) {
        if (PROVINCE_LIST.contains(province) == false) {
            return null;
        }
        List<String> cityList = new ArrayList<>();
        for (int i = 0; i < CITY_LIST.size(); i++) {
            if (Objects.equals(CITY_LIST.get(i).getKey(), province) == true) {
                cityList = CITY_LIST.get(i).getValue();
                break;
            }
        }
        getRandomList(cityList);
        return cityList.get(new SecureRandom().nextInt(0, cityList.size()));
    }
    public static String getRandomChineseProvinceCity() {
        String province = RandomChineseCountryRegion.getRandomChineseProvince();
        String city = RandomChineseCountryRegion.getRandomChineseCity(province);
        return province + city;
    }
    public static String getRandomChineseProvinceCity(String separator) {
        String province = RandomChineseCountryRegion.getRandomChineseProvince();
        String city = RandomChineseCountryRegion.getRandomChineseCity(province);
        return province + separator + city;
    }
}
class RandomDateTime {
    public static final long MINUTE = 60;
    public static final long HOUR = 60 * 60;
    public static final long DAY = 60 * 60 * 24;
    private RandomDateTime() {

    }
    public static long getNowSecondTimestamp() {
        return Instant.now().getEpochSecond();
    }
    public static long getRandomSecondTimestamp(long beginSecondTimestamp, long endSecondTimestamp) {
        return new SecureRandom().nextLong(beginSecondTimestamp, endSecondTimestamp);
    }
    public static LocalDateTime getDateTime(long secondTimestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(secondTimestamp), ZoneId.systemDefault());
    }
    public static LocalDateTime getRandomDatetime(long beginSecondTimestamp, long endSecondTimestamp) {
        return getDateTime(getRandomSecondTimestamp(beginSecondTimestamp, endSecondTimestamp));
    }
    public static String getDateTimeString(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
public class RandomTicket {
    private RandomTicket() {

    }
    public static Ticket getRandomTicket() {
        Ticket ticket = new Ticket();
        ticket.setOrigin(RandomChineseCountryRegion.getRandomChineseProvinceCity(""));
        ticket.setDestination(RandomChineseCountryRegion.getRandomChineseProvinceCity(""));
        long beginSecondTimestamp1 = RandomDateTime.getNowSecondTimestamp(); //当前时间
        long beginSecondTimestamp2 = beginSecondTimestamp1 + RandomDateTime.DAY * 7; //7 天内
        long takeoffSecondTimestamp = RandomDateTime.getRandomSecondTimestamp(beginSecondTimestamp1, beginSecondTimestamp2);
        Timestamp takeoff = Timestamp.valueOf(RandomDateTime.getDateTimeString(RandomDateTime.getDateTime(takeoffSecondTimestamp), ValidUtils.DATA_TIME_PATTERN1));
        ticket.setTakeoff(takeoff);
        long endSecondTimestamp1 = takeoffSecondTimestamp;
        long endSecondTimestamp2 = endSecondTimestamp1 + RandomDateTime.HOUR * 6; //6 小时内
        long landSecondTimestamp = RandomDateTime.getRandomSecondTimestamp(endSecondTimestamp1, endSecondTimestamp2);
        Timestamp land = Timestamp.valueOf(RandomDateTime.getDateTimeString(RandomDateTime.getDateTime(landSecondTimestamp), ValidUtils.DATA_TIME_PATTERN1));
        ticket.setLand(land);
        ticket.setPrice(Math.round(new SecureRandom().nextDouble(100, 1000) * 100) / 100.0); //保留 2 位小数
        return ticket;
    }
}