package Controller;

import static java.awt.Toolkit.getDefaultToolkit;

public class ScaleAnalyzing {
    private int dpi;
    public ScaleAnalyzing(){
        //Use class Toolkit to detect screen dpi
        setDpi(getDefaultToolkit().getScreenResolution());
    }
    public ScaleAnalyzing(int dpi){
        this.dpi = dpi;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }
    public double dpiToZoomScale(){
        return dpiToScaleFactor()/100;
    }
    public double dpiToZoomScale(int dpi){
        return dpiToZoomScale(dpi)/100;
    }
    public int dpiToScaleFactor(){
        return this.dpiToScaleFactor(getDpi());
    }
    public int dpiToScaleFactor(int dpi){
        switch (dpi){
            case 96:{
                return 100;
            }
            case 120:{
                return 125;
            }
            case 144:{
                return 150;
            }
            case 192:{
                return 200;
            }
            default:{
                return -1;
            }
        }
    }
}
