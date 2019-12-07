# ClickEffects
This is a simple Kotlin library that include some effects. 

[![](https://jitpack.io/v/johnjeremih/ClickEffects.svg)](https://jitpack.io/#johnjeremih/ClickEffects)

**This library was first released by
[WeselyOng](https://github.com/Wesely) and I made a Kotlin version of
it.** **I still working on some more effect.**


[Here is the original post](https://github.com/Wesely/Andrlid-Animation-ClickEffect)

`**This library is not ready for production. This is a pre-release.**`




USAGE
-----

To make a click effect add ClickEffects library in your project:

```groovy
implementation 'com.github.johnjeremih:ClickEffects:0.1.0'
```


Kotlin
-----
```kotlin
heart_image_button.setOnClickListener {
         ClickEffects.animParticalNova(context,it,18)
         ClickEffects.animExplosion(baseContext,it)
         ClickEffects.animRing(baseContext, it, 400)
         ClickEffects.animJumping(it)
         ClickEffects.animShaking(it)
}
```

Java
-----
```java
      mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickEffects.animParticalNova(context,v,18)
                ClickEffect.animExplosion(context, v);
                ClickEffect.animRing(context, v, 400);
                ClickEffects.animJumping(v)
                ClickEffect.animShaking(v);
                
            }
        });
```



