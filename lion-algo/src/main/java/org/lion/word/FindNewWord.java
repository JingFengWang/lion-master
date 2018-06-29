package org.lion.word;

import java.io.*;
import java.util.*;

public class FindNewWord {

    public boolean if_remove_oldWors = true;// 是否在n-gram阶段就滤掉旧词
    public String text="那被叫做孙总是一个年约三十开外，身材高大的大胖子，生了一张圆脸，笑起来倒是一团和气，西门金莲忍不住看了他一眼，他也友善的冲西门金莲笑笑：“我就说呗，老周这里一有新货，你都是赶早的，今儿这多早晚的不来，肯定是被什么事情绊住了，可不是？原来是被美女绊住了！”";
    public int textLength = text.length();

    int space_num = 4;
    public int len_max = 4;
    double compactness_t = 1000;
    double flexible_t = 0.5;
    double wcount_t = 5;
    public String cm = "99999999999999999";
    public int count_max = Integer.parseInt(cm.substring(0,space_num));

    public Set<String> wset = new HashSet();
    public HashMap<String,String> w_Map = new HashMap();
    public HashMap<String,String> nw_Map = new HashMap();


    public static Set<String> foundWords = new HashSet<String>();



    //
    // 开始统计词信息
    public void wordStatics(){
        text = text.replaceAll("[^\u4E00-\u9FA5^a-z^A-Z^0-9^\\>]", "").replaceAll(" ", "");//[^a-z^A-Z^0-9]
        textLength = text.length();
        if(textLength<2) return;

        // 初始化，减少包含检索
        String spacel = "                                  "; // 填充空格字符使用， 根据 space_num 填充特定数目的空格
//        System.out.println(spacel.length());
        String info_ini = 0+",的:0"+spacel.substring(0,space_num-1)+",的:0"+spacel.substring(0,space_num-1);
//        System.out.println("|"+spacel.substring(0,space_num-1)+"|");
//        System.out.println("|" + info_ini + "|");
        // n-gram (n=len_max)
        for(int i=0;i<textLength;i++){
            if(text.charAt(i)=='>') {
//                System.out.println(text.charAt(i));
                continue;
            }
            int flag = 0;//判断是否遇到句尾
            for(int j = 0; j <= len_max; j++){
                if(i+j+1 > textLength) continue;
                String cur_w = text.substring(i, i+j+1);//当前词
//                System.out.println(cur_w);

                if(cur_w.charAt(cur_w.length()-1)=='>'){//当前词末尾已经是完结符,则其不构成一个词，不用统计
                    flag = 1;
                }

                if(flag == 1) continue;
                // 初始化
                wset.add(cur_w);
                w_Map.put(cur_w, info_ini);
//                System.out.println(cur_w + "|" + info_ini);
            }
        }
//        for (String word : wset) {
//            System.out.println(word);
//        }


        // 词频、 左邻字、 右邻字 统计
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i)=='>') continue;

            int flag = 0;//判断是否遇到句尾 or 是旧词

            for(int j=0;j<=len_max;j++){ // 词长

                if(i+j+1 > textLength) continue;

                String cur_w = text.substring(i, i+j+1);//当前词
//                System.out.println(cur_w);

                if(cur_w.charAt(cur_w.length()-1)=='>'){//当前词末尾已经是完结符
                    flag = 1;
                }
                if(flag == 1) continue;

                //
                String info_all = w_Map.get(cur_w);
//                System.out.println(cur_w + "|" + info_all);

                String info[] = info_all.split(",");
//                System.out.println(Arrays.toString(info));

                int num = Integer.parseInt(info[0])+1; // 词频累加
                //
                String left_w = "";
                if(i != 0 && text.charAt(i-1) != '>')  left_w += text.charAt(i-1);

                String right_w = "";
                if( (i+j+1) < textLength && text.charAt(i+j+1) != '>')  right_w += text.charAt(i+j+1);

//                System.out.println("left:" + left_w);
//                System.out.println("right:" + right_w);

                info_all = updateWordStatic(info_all, left_w, "left");
//                System.out.println("单词计算右链接 ：" + cur_w);
                info_all = updateWordStatic(info_all, right_w, "right");

                info_all = info_all.substring((info_all.indexOf(',', 0)));

                w_Map.put(cur_w, num + info_all);
//                System.out.println(cur_w + " " + num + info_all);
//                if(cur_w.equals("现在")){
                    //System.out.println(cur_w+"==>:"+num+","+left_w+","+right_w);
//                }
            }
        }
    }

    // 更新词统计信息
    public String updateWordStatic(String str,String w_cur,String place){
        if(w_cur.length()<1) return str;
        String[] str_l = str.split(",");
        String spacel = "                                  ";
        if(place.equals("left")){
            int i_w = str_l[1].indexOf(w_cur+":"); // 找左字
            if(i_w > 0){
                int i_n = i_w + w_cur.length()+1;
                int i_b = i_n+space_num;
                int num_cur = 0;
                try {
                    num_cur = Integer.parseInt(str_l[1].substring(i_n, i_n+space_num).replaceAll(" ", ""))+1;
                } catch (NumberFormatException e) {
//                    System.out.println(i_w);
//                    System.out.println(i_n);
//                    System.out.println(i_b);
//                    System.out.println("获取字频 : " + str_l[1]);
//                    System.out.println(str);
//                    System.out.println(w_cur);
//                    System.out.println(place);
//                    System.out.println(str_l[2].substring(i_n, i_n+space_num));
                    num_cur = 2;
//                    throw new NumberFormatException("数字转换异常");
                }
                String nums = "                           ";
                nums = nums.substring(0,space_num);
                if(num_cur < count_max){
                    String num_t = num_cur +  "";
//                    System.out.println(num_t);
                    nums = num_t + nums.substring(num_t.length()); // 填补数字长度
//                    System.out.println("|" + nums + "|");
                } else num_cur = count_max;

                str = str_l[0] + ","+str_l[1].substring(0, i_w) + w_cur + ":" + nums + str_l[1].substring(i_b)+","+str_l[2];
//                System.out.println(str);
            } else {
                // 第一次出现的字
                str = str_l[0] + "," + str_l[1] + w_cur + ":" + "1" + spacel.substring(0,space_num-1) + "," + str_l[2];
//                System.out.println(str);
            }
        }

        if(place.equals("right")){
            int i_w = str_l[2].indexOf(w_cur+":");
            if(i_w > 0){
                int i_n = i_w + w_cur.length()+1;
                int i_b = i_n+space_num;
//                System.out.println("|" + str_l[2].substring(i_n, i_n+space_num).replaceAll(" ", "") + "|");
                int num_cur = 0;
                try {
                    num_cur = Integer.parseInt(str_l[2].substring(i_n, i_n+space_num).replaceAll(" ", ""))+1;
                } catch (NumberFormatException e) {
//                    System.out.println(i_w);
//                    System.out.println(i_n);
//                    System.out.println(i_b);
//                    System.out.println("获取字频 : " + str_l[2]);
//                    System.out.println(str);
//                    System.out.println(w_cur);
//                    System.out.println(place);
//                    System.out.println(str_l[2].substring(i_n, i_n+space_num));
                    num_cur = 2;
//                    throw new NumberFormatException("数字转换异常");
                }
                String nums = "                           ";
                nums = nums.substring(0,space_num);
                if(num_cur<count_max){
                    String num_t = num_cur +  "";
                    nums = num_t + nums.substring(num_t.length());
                }
                else num_cur=count_max;
                str = str_l[0] + ","+str_l[1]+","+str_l[2].substring(0, i_w) + w_cur+":"+nums + str_l[2].substring(i_b);
            } else {
                if ("莲".equalsIgnoreCase(w_cur)) {
//                    System.out.println("begin : " + str);
                    str = str_l[0] + "," + str_l[1] + "," + str_l[2] + w_cur + ":" + "1" + spacel.substring(0,space_num-1);
//                    System.out.println("end : " + str);
                } else {
                    str = str_l[0] + "," + str_l[1] + "," + str_l[2] + w_cur + ":" + "1" + spacel.substring(0,space_num-1);
                }

            }
        }
        return str;
    }

    // 计算词内凝固度、词外自由度 发现新词， 需要用到全局词表
    public HashMap<String,String> findWords() throws Exception{
        System.out.println("existing word num: "+foundWords.size());
        //
        for(String s:wset){
            if(s.length()>1 && s.replaceAll("[^\u4E00-\u9FA5]", "").length()>0){//[^a-z^A-Z^0-9]){
                // 计算词内凝固度
                String info[] = w_Map.get(s).split(",");
                double compact_score = Double.MAX_VALUE;
                Integer s0 = Integer.parseInt(info[0]);//词频
                for(int i = 0;i<s.length()-1;i++){
                    // 计算词内凝固度
                    //如果能分成两半
                    if(wset.contains(s.substring(0, i+1)) & wset.contains(s.substring(i+1))){
                        double s1 = 1.0*Integer.parseInt(w_Map.get(s.substring(0, i+1)).split(",")[0]);//词频
                        double s2 = 1.0*Integer.parseInt(w_Map.get(s.substring(i+1)).split(",")[0]);//词频
                        double score_comp = s0*textLength*1.0/(s1*s2);//分母是随机组合的概率,比值越大越是人组的语意的词

                        compact_score = Math.min(compact_score, score_comp );
//                        System.out.println("自由度： " + "当前词：" + s + ":" + s0 +
//                                " 左：" + s.substring(0, i+1) + ":" + w_Map.get(s.substring(0, i+1)).split(",")[0] +
//                                " 右:" + s.substring(i+1) + ":" + w_Map.get(s.substring(i+1)).split(",")[0] +
//                                " 左词频：" + s1 + " 右词频：" + s2 + " 凝固度（当前词频*文本总字数*1.0/（左词频*右词频））：" + score_comp + " 取最小值：" + compact_score);
                    }
                }

                // 计算词外自由度, 没有全局性
                double left_f = culWordsEtropy(info[1]);
                double right_f =culWordsEtropy(info[2]);
                double flexible = Math.min(left_f, right_f );

                // 如果满足条件就加入潜在新词
                if(compact_score>compactness_t
                        && flexible > flexible_t
                        && s0>wcount_t
                        && (!foundWords.contains(s)))
                    nw_Map.put(s, s0+","+compact_score+"," + flexible);
            }
        }
        return nw_Map;
    }

    // 计算词熵
    public double culWordsEtropy(String str){
        int sumc = 0;
        List<Integer> num_l =  new ArrayList<Integer>();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==':'){
                Integer temp = Integer.parseInt(str.substring(i+1, i+5).replaceAll(" ", ""));
                if(temp>0){
                    num_l.add(temp);
                    sumc += temp;
                }
            }
        }
        double strEtropy = 0.0;
        for(Integer num:num_l){
            strEtropy += (-1.0) * (num*1.0/(sumc*1.0)) * (Math.log(num*1.0/(sumc*1.0)));
//            System.out.println(strEtropy + "+= (-1.0) * (" + num + "*1.0/(" + sumc + "*1.0)) * (Math.log(" + num + "*1.0/("+sumc+"*1.0)))");
        }
        System.out.println("-----------------------------");
        return strEtropy;
    }

//    public static void saveStrCSV(String file, String conent) {
//        BufferedWriter out = null;
//        try {
//            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
//            out.write(conent+"\r\n");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void main(String args[]) throws Exception{
        String dataPath = "F:\\资本论（全）.txt";//西游记
//        String dataPath = "F:\\金瓶莲.txt";//西游记
//        String dataPath = "F:\\jx3_talk.txt";//西游记
        //
        InputStreamReader read = new InputStreamReader(new FileInputStream(dataPath),"gbk"); //,"gbk","UTF-8"
//        InputStreamReader read = new InputStreamReader(new FileInputStream(dataPath),"utf-8"); //,"gbk","UTF-8"
        @SuppressWarnings("resource")
        BufferedReader currBR = new BufferedReader(read);


        StringBuilder sb = new StringBuilder();
        String tempCurrLine = "";
        while ((tempCurrLine = currBR.readLine()) != null) {
//            System.out.println(tempCurrLine);
            sb.append(tempCurrLine);
        }
        String con = sb.toString();
//        System.out.println(con);
        //
        long begin = System.currentTimeMillis();

        FindNewWord fnw = new FindNewWord();
        fnw.text = con;
        fnw.wordStatics();
        HashMap<String,String> fw = fnw.findWords();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - begin));
        System.out.println("\n\n\nnew words num: " + fw.size());

        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(fw.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, String>>()
        {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                Integer num1 = Integer.parseInt(o1.getValue().split(",")[0]);
                Integer num2 = Integer.parseInt(o2.getValue().split(",")[0]);
                Double com1 = Double.valueOf(o1.getValue().split(",")[1]);
                Double com2 = Double.parseDouble(o2.getValue().split(",")[1]);
                //return com2.compareTo(com1);
                return num2.compareTo(num1);
            }
        });
        for (int i = 0; i < entryList.size(); ++i) {
            System.out.println(entryList.get(i).getKey()+"\t-->"+ entryList.get(i).getValue());
        }
    }

}
