package animation;

import java.awt.image.BufferedImage;

import controller.Loader;

public class Animated {
    BufferedImage image;
    private BufferedImage[] swingAnimation;
    Loader load = new Loader();

    public Animated() {
        image = (BufferedImage) load.mainimage();
    }

    public BufferedImage[] swingAnimation() {
        swingAnimation = new BufferedImage[7];
        // swingAnimation[0] = (BufferedImage) image.getSubimage(448, 0, 64, 64);
        swingAnimation[0] = (BufferedImage) Loader.loadImage(
                "src/assets/pedangkanan.png");
        System.out.println(swingAnimation[0]);
        swingAnimation[1] = (BufferedImage) Loader.loadImage(
                "src/assets/slahanimation/File1.png");
        swingAnimation[2] = (BufferedImage) Loader.loadImage(
                "src/assets/slahanimation/File2.png");
        swingAnimation[3] = (BufferedImage) Loader.loadImage(
                "src/assets/slahanimation/File3.png");
        swingAnimation[4] = (BufferedImage) Loader.loadImage(
                "src/assets/slahanimation/File4.png");
        swingAnimation[5] = (BufferedImage) Loader.loadImage(
                "src/assets/slahanimation/File5.png");
        swingAnimation[6] = (BufferedImage) Loader.loadImage(
                "src/assets/slahanimation/File6.png");
        return swingAnimation;
    }

    public BufferedImage[] shotanimation() {
        swingAnimation = new BufferedImage[3];
        swingAnimation[0] = (BufferedImage) image.getSubimage(448, 0, 64, 64);
        swingAnimation[1] = (BufferedImage) image.getSubimage(512, 128, 64, 64);
        swingAnimation[2] = (BufferedImage) image.getSubimage(448, 0, 64, 64);
        return swingAnimation;
    }

    public BufferedImage[] arrowshotanimation() {
        swingAnimation = new BufferedImage[3];
        swingAnimation[0] = (BufferedImage) image.getSubimage(448, 0, 64, 64);
        swingAnimation[1] = (BufferedImage) image.getSubimage(512, 128, 64, 64);
        swingAnimation[2] = (BufferedImage) image.getSubimage(448, 0, 64, 64);
        return swingAnimation;
    }
}
