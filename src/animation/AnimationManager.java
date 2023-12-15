package animation;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class AnimationManager {
    private BufferedImage[] animation;
    private int totalFrames;
    private BufferedImage image;
    private boolean isAnimating = false;
    private boolean ischarged = false;

    private JPanel panel;

    public AnimationManager(JPanel panel) {
        this.panel = panel;
    }

    public void startBlodAnimation() {
        Animated animated = new Animated();
        this.animation = animated.blodanimation();
        totalFrames = animation.length;
        Thread animationThread = new Thread(() -> {
            isAnimating = true;
            for (int i = 0; i < totalFrames; i++) {
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                image = animation[i];
                panel.repaint();
            }
            image = animation[0];
            isAnimating = false;
            panel.repaint();
        });
        animationThread.start();
    }

    public void charge1() {
        Animated animated = new Animated();
        this.animation = animated.chargeanimation1();
        totalFrames = animation.length;
        Thread animationThread = new Thread(() -> {
            isAnimating = true;
            for (int i = 0; i < totalFrames; i++) {
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                image = animation[i];
                panel.repaint();
            }
            image = animation[0];
            isAnimating = false;
            panel.repaint();
        });
        animationThread.start();
    }

    public void charge2() {
        Animated animated = new Animated();
        this.animation = animated.chargeanimation2();
        totalFrames = animation.length;
        Thread animationThread = new Thread(() -> {
            isAnimating = true;
            for (int i = 0; i < totalFrames; i++) {
                try {
                    Thread.sleep(135);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                image = animation[i];
                panel.repaint();
            }
            image = animation[0];
            isAnimating = false;
            panel.repaint();
        });
        animationThread.start();
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public boolean isIscharged() {
        return ischarged;
    }

    public BufferedImage getCurrentFrame() {
        return image;
    }
}
