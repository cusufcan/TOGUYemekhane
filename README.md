# 📱 TOGÜ Yemekhane

Tokat Gaziosmanpaşa Üniversitesi Sosyal Tesisler Yemekhane Menüsü'nü barındıran uygulama.

[![Get it on Google Play Store](https://upload.wikimedia.org/wikipedia/commons/7/78/Google_Play_Store_badge_EN.svg)](https://play.google.com/store/apps/details?id=com.mercan.app)

---

## 🛠️ Özellikler

- Haftalık çıkan yemeklerin listesi.
- İnternet erişimi olmadığında son kaydedilen haftaya ulaşım kolaylığı.
- Okulun bakiye görüntüleme ve yükleme sistemine kolay erişim.

---

## 📸 Ekran Görüntüleri
![TOGUYemekhaneLight](https://github.com/user-attachments/assets/4a97e9e5-7317-401d-a8ad-ca293ba3284e)
![TOGUYemekhaneDark](https://github.com/user-attachments/assets/dc404e63-8662-43b8-be96-d770e776d1b0)

---

## 🛠️ Kullanılan Teknolojiler

- **Kotlin Kapt**: Proje içinde veri bağlama veya DI (bağımlılık enjeksiyonu) işlemlerinde kullanılan, derleme zamanında anotasyonları işleyerek kod üretimini kolaylaştıran bir araçtır.
- **Kotlin Ksp**: Daha hızlı kod üretimi için kullanılan, özellikle Room gibi veri tabanı bileşenleriyle uyumlu bir sembol işleme aracıdır.
- **Hilt**: Uygulamanın modüler bir yapıda geliştirilmesi ve bağımlılıkların kolayca yönetilmesi için tercih edilen bir DI kütüphanesidir.
- **Jetpack Navigation**: Yemekhane uygulamasında farklı ekranlar (ör. menü listesi, bakiye yükleme) arasında kullanıcı dostu ve hatasız gezinmeyi sağlar.
- **Coroutines**: Ağ istekleri veya veritabanı işlemleri gibi asenkron görevlerin daha etkin bir şekilde yönetilmesine olanak tanır.
- **Jsoup**: Yemekhane bilgilerini bir web sayfasından almak için kullanılan HTML işleme ve veri çıkarma kütüphanesidir.
- **LifeCycle**: Uygulamanın yaşam döngüsüne bağlı bileşenlerin doğru yönetilmesi ve kaynakların optimize edilmesi için kullanılır.
- **Room**: Yerel SQLite veritabanıyla daha kolay ve güvenilir veri depolama işlemleri gerçekleştirilmesini sağlar.
- **Gson**: JSON formatındaki veriyle çalışarak, verilerin kolayca işlenip nesnelere dönüştürülmesini veya tersini sağlar.
- **WebView**: Kullanıcılara doğrudan uygulama içinde web içeriklerini (ör. bakiye yükleme) görüntüleme olanağı sunar.
- **Firebase Firestore**: Yemek menülerinin gerçek zamanlı olarak saklanması için kullanılan bulut tabanlı bir NoSQL veritabanıdır.
- **Firebase Cloud Messaging**: Kullanıcılara yeni yemek menüsü veya duyurularla ilgili anlık bildirimler göndermek için kullanılır.

---

## 📢 Proje Yapısı

```
/app
   /core
   /data
      /local
      /model
      /remote
      /repository
   /di
   /ui
      /adapter
         /menulist
         /viewpager
      /state
      /view
         /home
         /main
         /menudetail
         /wallet
      /viewmodel
   /util
```

---

## 🛡️ Lisans

Bu proje [MIT Lisansı](LICENSE) ile lisanslanmıştır. Daha fazla bilgi için `LICENSE` dosyasına
bakabilirsiniz.

---

## 📞 İletişim

Herhangi bir soru veya öneriniz varsa, benimle iletişime geçebilirsiniz:

- LinkedIn: [linkedin.com/in/cusufcan](https://linkedin.com/in/cusufcan)
- Email: [yusufcanmercan.info@gmail.com](mailto:yusufcanmercan.info@gmail.com)
