package com.github.annasajkh;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Ball
{
	float x, y;
	float radius;
	
	Vector2 velocity;
	
	public Ball()
	{
		this.radius = MathUtils.random(10,40);
		
		this.x = MathUtils.random(radius ,Game.width - radius);
		this.y = MathUtils.random(radius, Game.height - radius);
		
		velocity = new Vector2(1,0).setToRandomDirection().scl(MathUtils.random(100,200));
	}
	
	public void update(float delta)
	{
		
		x += velocity.x * delta;
		y += velocity.y * delta;
		
		x = MathUtils.clamp(x,radius,Game.width - radius);
		y = MathUtils.clamp(y,radius,Game.height - radius);
		
		if(x == Game.width - radius || x == radius)
		{
			velocity.x *= -1;
		}
		else if(y == Game.height - radius || y == radius)
		{
			velocity.y *= -1;
		}
	}
	
	
	
	
}
