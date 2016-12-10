package bigNumCalculate;

class StringWrapper{
    private String value;

    StringWrapper(String string){
        this.value = string;
    }

    public void setValue(String string){
        this.value = string;
    }

    public String getValue(){
        return this.value;
    }
}