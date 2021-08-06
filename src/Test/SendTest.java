package Test;

/**
 * @author aidem
 * @date 2021-01-20
 * @description code
 */
public class SendTest {

    private String email;
    private Post content;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Post getContent() {
        return content;
    }

    public void setContent(Post content) {
        this.content = content;
    }

    public static class Post {
        private ZhCn zhCn;

        public ZhCn getZhCn() {
            return zhCn;
        }

        public void setZhCn(ZhCn zhCn) {
            this.zhCn = zhCn;
        }
    }

    public static class ZhCn {
        private String title;
        private Content content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Content getContent() {
            return content;
        }

        public void setContent(Content content) {
            this.content = content;
        }
    }

    public static class Content {
        private TextContent textContent;

        public TextContent getTextContent() {
            return textContent;
        }

        public void setTextContent(TextContent textContent) {
            this.textContent = textContent;
        }
    }

    public static class TextContent {
        private String tag;
        private String text;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }


}
