package com.bingo.customer.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.bingo.customer.R;

import java.util.ArrayList;

/**
 * ================================
 * 作   者:   zcb
 * 邮   箱:   zhang-cb@foxmail.com
 * 创建时间:   2017/6/5 14:18
 * 版   本:   1.0
 * 描   述:   自定义芝麻分数等级view
 * 首先需要设置数据setdata，data中值的范围需要从小到大排序
 * 然后设置等级的值setCurrentDialvalue，即可
 * =================================
 */

public class UserLevelView extends View {
    private Context context;
    private String Tag = "UserLevelView";
    private Paint paint_bg = new Paint();
    private Paint paint_text_value = new Paint();
    private Paint paint_text_total = new Paint();
    private Paint hanziPaint = new Paint();
    private Paint recPaint = new Paint();

    private LinearGradient linearGradient;
    private ArrayList<UserLevelDial> datas;
    private int currentDialvalue = -1;
    //整个控件的长度
    private int allLength;
    //有背景文字的等级汉字的长度
    private int cDialLenth;

    //笔触宽度
    private int cap = 10;
    //进度条宽度
    private int lineWidth = 15;

    private int[] level_num = {350, 550, 600, 650, 700, 950};
    private int currentImg = -1;
    //图片等级与线条的间距
    private int padding_img = 20;
    //线条与上面文字的间距
    private int padding_text = 20;
    private int textHeight = 0;
    private int textSize;
    private int maxValue = 1500;
    private String[] paintString = {getResources().getString(R.string.text_sesame_level_jiaocha),
            getResources().getString(R.string.text_sesame_level_zhongdeng),
            getResources().getString(R.string.text_sesame_level_lianghao),
            getResources().getString(R.string.text_sesame_level_youxiu),
            getResources().getString(R.string.text_sesame_level_jihao)};

    private ArrayList<LineColor> lineColors = new ArrayList<>();

    public UserLevelView(Context context) {
        this(context, null);
        this.context = context;
    }

    public UserLevelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public UserLevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public void init() {
        this.textSize = DensityUtil.dip2px(getContext(), 10f);
        this.textHeight = getTextWH("9", textSize).height();
        initPaint();
        initLineColor();
    }


    public void initPaint() {

        //第二条背景线的画笔
        paint_bg.setColor(ContextCompat.getColor(context, R.color.gray));
        paint_bg.setStyle(Paint.Style.FILL);
//        paint_bg.setStrokeCap(Paint.Cap.ROUND);
        paint_bg.setStrokeJoin(Paint.Join.ROUND);
        paint_bg.setStrokeWidth(lineWidth);
        paint_bg.setAntiAlias(true);

        //选中值后的有颜色背景的汉字画笔
        paint_text_value.setTextSize(textSize);
        paint_text_value.setColor(ContextCompat.getColor(context, R.color.userlevels2));
        paint_text_total.setTextSize(textSize);
        paint_text_total.setColor(Color.BLACK);

        //汉字画笔
        hanziPaint.setStrokeWidth(3);
        hanziPaint.setTextSize(textSize);
        hanziPaint.setColor(Color.GRAY);
        hanziPaint.setTextAlign(Paint.Align.LEFT);
        hanziPaint.setAntiAlias(true);

        //渐变色矩形画笔
        recPaint.setStyle(Paint.Style.FILL);//充满
        recPaint.setColor(Color.LTGRAY);
        recPaint.setAntiAlias(true);// 设置画笔的锯齿效果
    }

    private void initLineColor() {
        lineColors.add(new LineColor(ContextCompat.getColor(context, R.color.jiaocha_start), ContextCompat.getColor(context, R.color.jiaocha_end)));
        lineColors.add(new LineColor(ContextCompat.getColor(context, R.color.zhongdeng_start), ContextCompat.getColor(context, R.color.zhongdeng_end)));
        lineColors.add(new LineColor(ContextCompat.getColor(context, R.color.lianghao_start), ContextCompat.getColor(context, R.color.lianghao_end)));
        lineColors.add(new LineColor(ContextCompat.getColor(context, R.color.youxiu_start), ContextCompat.getColor(context, R.color.youxiu_end)));
        lineColors.add(new LineColor(ContextCompat.getColor(context, R.color.jihao_start), ContextCompat.getColor(context, R.color.jihao_end)));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getPaddingTop() + getPaddingBottom() + lineWidth + padding_img + 20 + textHeight + padding_text;
        Log.i(Tag, "height:" + height);
        Log.i(Tag, "getPaddingBottom():" + getPaddingBottom() + ",getPaddingTop():" + getPaddingTop() + "padding_img:" + padding_img + "lineWidth" + lineWidth);
        setMeasuredDimension(getMeasuredWidth(), height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画第一行 5个等级的文字
        Rect rect = getTextWH(currentDialvalue + "", textSize);
        //修正边界
        Log.i(Tag, "textgetPaddingTop:" + getPaddingTop());
        int text_ph = getPaddingTop() + rect.height() - 7;

        if (currentDialvalue >= 350 && currentDialvalue < 550) {
            recPaint.setColor(ContextCompat.getColor(context, R.color.jiaocha_start));
            draw5Characters(canvas, 1,  text_ph);
        } else if (currentDialvalue >= 550 && currentDialvalue < 600) {
            recPaint.setColor(ContextCompat.getColor(context, R.color.zhongdeng_start));
            draw5Characters(canvas, 2,  text_ph);
        } else if (currentDialvalue >= 600 && currentDialvalue < 650) {
            recPaint.setColor(ContextCompat.getColor(context, R.color.lianghao_start));
            draw5Characters(canvas, 3, text_ph);
        } else if (currentDialvalue >= 650 && currentDialvalue < 700) {
            recPaint.setColor(ContextCompat.getColor(context, R.color.youxiu_start));
            draw5Characters(canvas, 4,  text_ph);
        } else if (currentDialvalue >= 700 && currentDialvalue < 950) {
            recPaint.setColor(ContextCompat.getColor(context, R.color.jihao_start));
            draw5Characters(canvas, 5, text_ph);
        }

        //画第二行不同颜色矩形
        int bgline_ph = lineWidth / 2 + 1 + getPaddingTop() + textHeight + padding_text;
        draw5Line(canvas, bgline_ph);
        //画第三行等级数字
        drawLevelNum(canvas);
    }

    /**
     * 画第一行5个等级词语{较差,中等,良好,优秀,极好}
     * @param canvas 画布
     * @param index 第一个需要背景
     * @param text_ph 高
     */
    public void draw5Characters(Canvas canvas, int index, int text_ph) {

        final float stepWidth = (getMeasuredWidth() - getPaddingRight() - cap) / 5;
        final float offsetWidth = getPaddingLeft() + stepWidth / 3;

        for (int i = 1; i <= 5; i++) {
            if (i == index) {
                // 背景矩形
                float left = stepWidth * (i - 1) + offsetWidth - 15;
                float top = getPaddingTop() - 20;
                float right = left + getTextWH("测试", textSize).width() + 30;
                float bottom = text_ph + 15;
                RectF oval3 = new RectF(left, top, right, bottom);
                canvas.drawRoundRect(oval3, 6, 6, recPaint);

                /**
                 * 三角形
                 *  *******
                 *   *****
                 *    ***
                 *     *
                 */
                Path path = new Path();
                path.moveTo(left, getPaddingTop());// 三角形的起顶点
                path.lineTo(right, getPaddingTop());//三角形右边的顶点
                path.lineTo((left + right) / 2, bottom + 10);  //三角形下面的顶点
                path.close(); // 使这些点构成封闭的多边形
                canvas.drawPath(path, recPaint);

                paint_text_value.setColor(Color.WHITE);
                canvas.drawText(paintString[i - 1], stepWidth * (i - 1) + offsetWidth, text_ph, paint_text_value);

            } else {
                canvas.drawText(paintString[i - 1], stepWidth * (i - 1) + offsetWidth, text_ph, hanziPaint);
            }
        }
    }

    /**
     * 画第二行5条等级颜色渐变线
     */
    public void draw5Line(Canvas canvas, int bgline_ph) {
        float startXPoint;
        float stopXPoint;
        float tempXPoint;

        for (int i = 1; i <= 5; i++) {
            startXPoint = (getMeasuredWidth() - getPaddingRight() - cap) / 5 * (i - 1) + getPaddingLeft();
            stopXPoint = (getMeasuredWidth() - getPaddingRight() - cap) / 5 * i + getPaddingLeft();
            if (i % 2 == 0) {  //第偶数次循环,交换起始点
                tempXPoint = startXPoint;
                startXPoint = stopXPoint;
                stopXPoint = tempXPoint;
            }

            linearGradient = new LinearGradient(startXPoint, 0, stopXPoint, 0, new int[]{lineColors.get(i - 1).startColor, lineColors.get(i - 1).stopColor}, null, LinearGradient.TileMode.CLAMP);
            paint_bg.setShader(linearGradient);
            canvas.drawLine(startXPoint, bgline_ph, stopXPoint, bgline_ph, paint_bg);
        }
    }

    /**
     * 画第三行的等级数字{350,550,600,650,70,950}
     *
     * @param canvas
     */
    public void drawLevelNum(Canvas canvas) {
        Paint mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setTextSize(25);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPaint.setTextAlign(Paint.Align.CENTER);

        int img_postion_h = lineWidth + padding_img + getPaddingTop() + textHeight + padding_text;
        Log.i(Tag, "img_postion_h:" + img_postion_h);
        float pre = allLength / 5;
        float offsetw = 20 / 3 * -1;
        for (int i = 0; i < level_num.length; i++) {
            if (i == level_num.length - 1) {
                canvas.drawText(level_num[i] + "", allLength + getPaddingLeft() + cap, img_postion_h + 25, mPaint);
            } else {
                if (i == 0) {
                    canvas.drawText(level_num[i] + "", i * pre + getPaddingLeft() + cap, img_postion_h + 25, mPaint);
                } else {
                    canvas.drawText(level_num[i] + "", i * pre + offsetw + getPaddingLeft() + cap + 20, img_postion_h + 25, mPaint);
                }
            }
        }
    }


    /**
     * 设置有多少个刻度，每个刻度的返回{0,50}，{50,x},{x,x2}....
     * 必须按照大小排好序再传递进来
     */
    public void setData(ArrayList<UserLevelDial> datas) {
        this.datas = datas;
        if (null != datas && datas.size() > 0) {
            maxValue = datas.get(datas.size() - 1).dial_e;
        }
    }

    //数值变化的动画
    ValueAnimator valueAnimator;

    /**
     * 设置当前刻度
     */
    public void setCurrentDialvalue(int dial) {
        if (null != valueAnimator && valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
        valueAnimator = ValueAnimator.ofInt(0, dial);
        valueAnimator.setDuration(5);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentDialvalue = (int) valueAnimator.getAnimatedValue();
                getCurrentDialLength();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    //根据当前值，按照比例，计算它该有的长度，每个刻度的长度一样，但是每个刻度的差值不一样，
    //这里就根据比例来计算
    public int getCurrentDialLength() {
        getDialAllLength();
        if (allLength > 0 && datas.size() > 0 && currentDialvalue > 0) {
            int pre = allLength / (datas.size());
            for (int i = 0; i < datas.size(); i++) {
                UserLevelDial dial = datas.get(i);
                if (currentDialvalue <= dial.dial_e && currentDialvalue >= dial.dial_s) {
                    this.currentImg = i;
                    cDialLenth = pre * i + (int) (pre * ((currentDialvalue - dial.dial_s) / ((float) dial.dial_e - dial.dial_s)));
                    Log.i(Tag, "i:" + i);
                    break;
                }
            }
        }
        if (currentDialvalue > datas.get(datas.size() - 1).dial_e) {
            this.currentImg = datas.size();
            cDialLenth = allLength;
        }
        Log.i(Tag, "cDialLenth:" + cDialLenth);
        //重新设置等级图片
        return cDialLenth;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        getDialAllLength();
    }

    //得到整个长度，出去padding
    public void getDialAllLength() {
        allLength = getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - cap * 2;
    }

    /**
     * @param text
     * @param textSize
     * @return
     */
    public Rect getTextWH(String text, int textSize) {
        Paint pFont = new Paint();
        Rect rect = new Rect();
        pFont.setTextSize(textSize);
        pFont.getTextBounds(text, 0, text.length(), rect);
        return rect;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(Tag, "onDetachedFromWindow");
    }


    public class LineColor {

        public LineColor(int startColor, int stopColor) {
            this.startColor = startColor;
            this.stopColor = stopColor;
        }

        public int startColor;
        public int stopColor;
    }
}
