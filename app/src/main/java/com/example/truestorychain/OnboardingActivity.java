package com.example.truestorychain;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button nextButton;
    private List<OnboardingItem> onboardingItems;
    private int currentIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.onboardingViewPager);
        nextButton = findViewById(R.id.nextButton);

        setupOnboardingItems();

        OnboardingAdapter adapter = new OnboardingAdapter(onboardingItems);
        viewPager.setAdapter(adapter);

        nextButton.setOnClickListener(v -> {
            if (currentIndex < onboardingItems.size() - 1) {
                currentIndex++;
                viewPager.setCurrentItem(currentIndex);
            } else {
                // ✅ Переход на экран регистрации
                Intent intent = new Intent(OnboardingActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish(); // Закрываем onboarding
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                nextButton.setText(position == onboardingItems.size() - 1 ? "Начать" : "Далее");
            }
        });
    }

    private void setupOnboardingItems() {
        onboardingItems = new ArrayList<>();
        onboardingItems.add(new OnboardingItem(
                R.drawable.ic_launcher_foreground,
                "Создавай истории",
                "Начни писать фанфик или крипипасту с нейтрального начала."));

        onboardingItems.add(new OnboardingItem(
                R.drawable.ic_launcher_foreground,
                "Голосуй",
                "Выбирай лучшее продолжение вместе с другими пользователями."));

        onboardingItems.add(new OnboardingItem(
                R.drawable.ic_launcher_foreground,
                "Блокчейн",
                "Авторство каждого куска текста зафиксировано навсегда."));

        onboardingItems.add(new OnboardingItem(
                R.drawable.ic_launcher_foreground,
                "Финал",
                "История завершается, когда большинство решит — это конец."));
    }
}
