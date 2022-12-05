package cu.edu.cujae.pweb.dto;

public class MatterDto {
    private Long matterId;
    private String matterName;


    public Long getMatterId() {
        return matterId;
    }

    public void setMatterId(Long matterId) {
        this.matterId = matterId;
    }

    public String getMatterName() {
        return matterName;
    }

    public void setMatterName(String matterName) {
        this.matterName = matterName;
    }
}
