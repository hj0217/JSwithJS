package sec03.brd07;

import java.sql.Date;
//Value Object 불변!
public class ArticleVO {
	private int level; // 추가
    private int articleNO;
    private int parentNO;
    private String title;
    private String content;
    private String imageFileName;
    private Date writedate;
    private String id;

    public ArticleVO() {
		super();
	}

    


	public ArticleVO(int articleNO, int parentNO, String title, String content, String imageFileName, String id) {
		super();

		this.articleNO = articleNO;
		this.parentNO = parentNO;
		this.title = title;
		this.content = content;
		this.imageFileName = imageFileName;
		this.id = id;
	}
//<1. level>
	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}
//<2. article>
	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}
//<3. ParentNO>
	public int getParentNO() {
		return parentNO;
	}

	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}
//<4. title>
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
//<5. content>
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
//<6. ImageFileName>
	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
//<7. writeDate>
	public Date getWritedate() {
		return writedate;
	}

	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
//<8. id>
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
