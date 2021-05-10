package com.github.annasajkh;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Game extends ApplicationAdapter
{
	ShapeRenderer shapeRenderer;
	Ball[] balls;

	public static float width, height;
	
	public static Color hsvToRgba(float hue, float saturation, float value, float alpha)
    {

        int h = (int) (hue * 6);
        float f = hue * 6 - h;
        float p = value * (1 - saturation);
        float q = value * (1 - f * saturation);
        float t = value * (1 - (1 - f) * saturation);

        switch (h)
        {
            case 0:
                return new Color(value, t, p, alpha);
            case 1:
                return new Color(q, value, p, alpha);
            case 2:
                return new Color(p, value, t, alpha);
            case 3:
                return new Color(p, q, value, alpha);
            case 4:
                return new Color(t, p, value, alpha);
            case 5:
                return new Color(value, p, q, alpha);
            default:
                throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " +
                                           hue +
                                           ", " +
                                           saturation +
                                           ", " +
                                           value);
        }
    }
	
	@Override
	public void create()
	{
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		shapeRenderer = new ShapeRenderer();
		
		balls = new Ball[10];
		
		for(int i = 0; i < balls.length; i++)
		{
			balls[i] = new Ball();
		}
	}
	
	public void update()
	{
		for(Ball ball : balls)
		{
			ball.update(Gdx.graphics.getDeltaTime());
		}
	}

	@Override
	public void render()
	{
		update();
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.begin(ShapeType.Point);
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				float sum = 0;
				
				for(Ball ball : balls)
				{
					float distance = Vector2.dst2(x, y, ball.x,ball.y);
					sum += ball.radius * ball.radius / distance;
					
				}
				
				sum = MathUtils.clamp(sum,0,0.999999f);
				shapeRenderer.setColor(hsvToRgba(sum,1,1,1));
				shapeRenderer.point(x,y, 0);
			}
		}
		shapeRenderer.end();
		
	}

	@Override
	public void dispose()
	{
		shapeRenderer.dispose();
	}
}
