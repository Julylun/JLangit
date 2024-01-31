package Model.JulyException;

import Model.Word;

public class WrongExtensionException extends Exception{
    public WrongExtensionException(String str){
        super(str);
    }
}
