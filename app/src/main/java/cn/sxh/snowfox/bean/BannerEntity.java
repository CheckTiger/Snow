package cn.sxh.snowfox.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by snow on 2017/8/7.
 */

public class BannerEntity implements Serializable{

    /**
     * success : true
     * code : 0
     * reason : 成功
     * time : 9.404
     * result : [{"id":11,"name":"封面.jpg","image":"http://51qunawan.cn/QunawanWeb/upload/webImg/contents/1478235073811.jpg","atype":1,"link":"http://www.baidu.com","weight":5,"content":"<p>5<\/p>"},{"id":12,"name":"农餐.jpg","image":"http://51qunawan.cn/QunawanWeb/upload/webImg/contents/1478235269007.jpg","atype":1,"link":"","weight":4,"content":"<p>4<\/p>"},{"id":13,"name":"农宿.jpg","image":"http://51qunawan.cn/QunawanWeb/upload/webImg/contents/1478235298129.jpg","atype":1,"link":"","weight":3,"content":"<p>3<\/p>"},{"id":16,"name":"nongwan.jpg","image":"http://51qunawan.cn/QunawanWeb/upload/webImg/contents/1478235490379.jpg","atype":1,"link":"","weight":2,"content":"<p>2<\/p>"},{"id":17,"name":"nongyoujpg","image":"http://51qunawan.cn/QunawanWeb/upload/webImg/contents/1478235511670.jpg","atype":1,"link":"","weight":1,"content":"<p>1<\/p>"},{"id":5,"name":"n_go.png","image":"http://www.51qunawan.cn/QunawanService/resources/advertising/n_go.png","atype":2,"link":"","weight":0,"content":"组团HTML内容"},{"id":6,"name":"n_eat.png","image":"http://www.51qunawan.cn/QunawanService/resources/advertising/n_eat.png","atype":3,"link":"","weight":0,"content":"众筹HTML内容"}]
     */

    private boolean success;
    private int code;
    private String reason;
    private double time;
    private List<ResultBean> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 11
         * name : 封面.jpg
         * image : http://51qunawan.cn/QunawanWeb/upload/webImg/contents/1478235073811.jpg
         * atype : 1
         * link : http://www.baidu.com
         * weight : 5
         * content : <p>5</p>
         */

        private int id;
        private String name;
        private String image;
        private int atype;
        private String link;
        private int weight;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getAtype() {
            return atype;
        }

        public void setAtype(int atype) {
            this.atype = atype;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
