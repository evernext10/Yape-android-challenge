# YAPE Challenge - Android Clean Architecture with MVVM using Jetpack component


## 💡 What is Clean Architecture?

Clean architecture is a category of software design pattern for software architecture that follows the concepts of clean code and implements [SOLID principles](https://www.jbsolutions.in/download/downloads/SOLID_PRINCIPLE_WITH_KOTLIN.pdf)

It’s essentially a collection of best practice design principles that help you keep business logic, or domain logic, together and minimize the dependencies within the system.

Clean architecture is a method of software development in which you should be able to identify what a program performs merely by looking at its source code. Robert C. Martin, also known as Uncle Bob, came up with the Clean Architecture concept in the year 2012.

![alt tag](https://github.com/evernext10/Yape-android-challenge/blob/main/resources/clean_architecture_software.jpg)

## 💡 Why Clean Architecture?

Separation of Concerns — Separation of code in different modules or sections with specific responsibilities making it easier for maintenance and further modification.
Loose coupling — flexible code anything can be easily be changed without changing the system
Easily Testable


## 💡 Layers of clean architecture

- **Presentation or UI:**
  A layer that interacts with the UI, mainly Android Stuff like Activities, Fragments, ViewModel, etc. It is dependent on Use Cases.
- **Domain:**
  Contains the business logic of the application. It is the individual and innermost module.
- **Data:**
  It includes the domain layer. It would implement the interface exposed by domain layer and dispenses data to app

![alt tag](https://github.com/evernext10/Yape-android-challenge/blob/main/resources/clean_arch.png)

## 💡 Advantages of Using Clean Architecture
- Easily testable.
- Scalable.
- Your team can add new features even more quickly.
- The project is even easier to maintain.

## 💡 Tech stack & Modern Library Tools

- [Kotlin, Coroutines + Flow for asynchronous](https://developer.android.com/kotlin/coroutines)
- [Dependency injection with Koin](https://developer.android.com/training/dependency-injection)
- [Jetpack Navigation](https://developer.android.com/guide/navigation/)
- [ViewModel - UI related data holder, lifecycle aware](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [MVVM Architecture (View - DataBinding - ViewModel - Model)](https://developer.android.com/topic/libraries/view-binding)
- [Glide - loading images](https://github.com/bumptech/glide)
- [Retrofit2 & OkHttp3 - construct the REST APIs and paging network data](https://square.github.io/retrofit/)
- [Gson - JSON representation](https://github.com/google/gson)
- [Material-Components - Material design components](https://material.io/design)
- [Leak Canary - memory leak detection library for Android](https://github.com/square/leakcanary)
- [AndroidJUnitRunner](https://developer.android.com/training/testing/junit-runner)
- [Truth](https://truth.dev/)
- [Turbine](https://github.com/cashapp/turbine)
- [CI/CD - GitActions](https://proandroiddev.com/android-ci-cd-pipeline-with-github-actions-demystifying-github-actions-83258e76a18f)


## 💡 Project: YAPE Challenge

This is a test presentation of Clean Architecture with MVVM in Android to show recipes from Mock API.


##  💡 Modules

**Authentication**

- core
- features
  - marketplace-product-list
    - data
    - domain
    - presentation
  - marketplace-product-detail
    - data
    - domain
    - presentation
- navigation
- app
- DSL

# Mock data
URL: https://demo0118588.mockable.io/recipes
```json
{
  "query":"query",
  "paging":{
    "total":10,
    "offset":5,
    "limit":1,
    "primary_results":10
  },
  "results":[
    {
      "id":"011",
      "name":"Mushroom Pasta with Parmesan",
      "average_cost" : 12443,
      "average_time" : 120,
      "type" : "Pasta",
      "image":"https://www.acouplecooks.com/wp-content/uploads/2021/05/Mushroom-Pasta-011.jpg",
      "description":"Calling all mushroom lovers! Here’s the meal for you: this Mushroom Pasta with Garlic Butter Sauce! It’s creamy and savory, harnessing the power of lemon, butter and Parmesan cheese. It makes the most of the intense umami of mushrooms: one of the most powerful ingredients in all of cooking. This dish has just the right proportion of ‘shrooms to pasta, so that each bite has a generous pop of flavor. We couldn’t stop shoveling in bites. In fact, our 4 year old told us he wanted to eat it forever (the best compliment).",
      "location":{
        "latitude":6.254505,
        "longitude":-75.580996
      },
      "pictures":[
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/05/Mushroom-Pasta-004.jpg"
        },
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/05/Mushroom-Pasta-010.jpg"
        }
      ]
    },
    {
      "id":"011",
      "name":"Quesadillas in the Oven",
      "average_cost" : 125443,
      "average_time" : 30,
      "type" : "Mexican",
      "image":"https://www.acouplecooks.com/wp-content/uploads/2021/05/Baked-Quesadilla-in-Oven-001-NEW.jpg",
      "description":"Want to make quesadillas for a crowd, but don’t want to hover over the stove while making them to order? Enter: quesadillas in the oven! This recipe makes the humble solo meal into a delicious dinner, resulting in a big batch all at once on a sheet pan. All the quesadillas are done at the same time, and they come out with a beautifully crispy exterior. In fact, it feels like a whole new version of this classic: like Quesadillas 2.0. Here’s how to whip up a pan of baked sheet pan quesadillas that are so good, everyone will be begging for more. (Everyone around our table was: true story!)",
      "location":{
        "latitude":6.254505,
        "longitude":-75.580996
      },
      "pictures":[
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/04/Baked-Quesadilla-in-Oven-003.jpg"
        },
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/04/Baked-Quesadilla-in-Oven-005.jpg"
        }
      ]
    },
    {
      "id":"011",
      "name":"Cheese Tortellini in Creamy Marinara",
      "average_cost" : 10443,
      "average_time" : 20,
      "type" : "Pasta",
      "image":"https://www.acouplecooks.com/wp-content/uploads/2021/03/Cheese-Tortellini-011.jpg",
      "description":"Some meatless recipes stand a head above the rest, and this is one of them. In fact, I think we’d be happy eating this genius quick dinner every day of the week. Try this cheese tortellini in creamy marinara sauce! In just 15 minutes, you’ve got a pasta skillet that’s a literal symphony of flavor. The fresh basil-infused marinara sauce is perfectly complimented with pillows of tangy goat cheese. (In fact, it’s our baked goat cheese dip made into a main dish, because we love it so much we needed a way to justify it as dinner.) This one is a huge hit in our house…and we hope it will be in yours too!",
      "location":{
        "latitude":6.254505,
        "longitude":-75.580996
      },
      "pictures":[
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/03/Cheese-Tortellini-008.jpg"
        },
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/03/Cheese-Tortellini-014.jpg"
        }
      ]
    },
    {
      "id":"011",
      "name":"Easy Eggplant Parmesan",
      "average_cost" : 1443,
      "average_time" : 10,
      "type" : "Pasta",
      "image":"https://www.acouplecooks.com/wp-content/uploads/2022/07/Eggplant-Parmesan-014.jpg",
      "description":"It’s got bubbly cheese, garlicky tomato sauce, and savory, meaty breaded eggplant: it’s Eggplant Parmesan! This vegetarian Italian classic is hearty, filling, and pleases just about everyone. Most restaurants use fried eggplant, but for a homemade version it’s much simpler to bake. Here’s our best baked Eggplant Parmesan recipe: faster and easier to make at home than most recipes and full of incredible flavor. Even our vegetable-averse 5 year old promptly asked for seconds! We hope it will become a go-to in your family like it is in ours.",
      "location":{
        "latitude":6.254505,
        "longitude":-75.580996
      },
      "pictures":[
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2022/07/Eggplant-Parmesan-007.jpg"
        },
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2022/07/Eggplant-Parmesan-019.jpg"
        }
      ]
    },
    {
      "id":"011",
      "name":"Epic Vegetarian Fajitas",
      "average_cost" : 12443,
      "average_time" : 70,
      "type" : "Vegan",
      "image":"https://www.acouplecooks.com/wp-content/uploads/2021/05/Mushroom-Pasta-011.jpg",
      "description":"Here’s a Tex Mex favorite that no one can turn down…and it’s a great way to infuse loads of veggies without giving it a second thought. Try these Vegetarian Faijtas! These are so full flavored and satisfying, they please everyone around the table: even the meat lovers! This fajita recipe stars our favorite fajita veggies, blackened in the oven and finished on the stove, with pinto beans cooked in a flavorful fajita sauce. It’s so satisfying, you’ll promptly add it to your regular rotation.",
      "location":{
        "latitude":6.254505,
        "longitude":-75.580996
      },
      "pictures":[
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/02/Vegetarian-Fajitas-003.jpg"
        },
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/02/Fajita-Veggies-003.jpg"
        }
      ]
    },
    {
      "id":"011",
      "name":"Coconut Tofu Curry",
      "average_cost" : 4243,
      "average_time" : 60,
      "type" : "Pasta",
      "image":"https://www.acouplecooks.com/wp-content/uploads/2021/03/Tofu-Curry-011.jpg",
      "description":"Here’s a dinner that takes just 30 minutes and results in so much flavor…you’ll put it on your regular rotation immediately. Try this Tofu Curry recipe! The brilliant red broth is flavored with coconut milk and red curry paste, with the zing of lime and a hint of spicy sweetness. The Thai-style flavors are the perfect way to introduce tofu to those wary of it. And if you’re already a lover (like us!), you’ll appreciate this delightfully colorful way to serve it. Here’s what to know!",
      "location":{
        "latitude":6.254505,
        "longitude":-75.580996
      },
      "pictures":[
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/03/Tofu-Curry-017.jpg"
        },
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/03/Tofu-Curry-012.jpg"
        }
      ]
    },
    {
      "id":"011",
      "name":"Easy Vegetarian Enchiladas",
      "average_cost" : 12443,
      "average_time" : 120,
      "type" : "Vegan",
      "image":"https://www.acouplecooks.com/wp-content/uploads/2021/03/Vegetarian-Enchiladas-002.jpg",
      "description":"Enchiladas are one of our favorite dinner recipes around here. But they can take a while to prep for a weeknight. Here’s a new recipe we designed to work in 30 minutes flat: these easy Vegetarian Enchiladas! The filling takes no time to whip up: it’s just spiced pinto beans and spices. Roll ’em up, bake until golden and cheesy, and load with all your favorite toppings. These are so quick, we’ve ditched our other favorite recipes for this one (oops!).",
      "location":{
        "latitude":6.254505,
        "longitude":-75.580996
      },
      "pictures":[
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/05/Mushroom-Pasta-004.jpg"
        },
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/05/Mushroom-Pasta-010.jpg"
        }
      ]
    },
    {
      "id":"011",
      "name":"Easy BBQ Tofu",
      "average_cost" : 56434,
      "average_time" : 120,
      "type" : "Pork",
      "image":"https://www.acouplecooks.com/wp-content/uploads/2021/06/BBQ-Tofu-003.jpg",
      "description":"Here’s a must-make recipe for tofu fans: and eaters on the fence, too. Try this irresistible BBQ Tofu recipe! Each bite has just the right chewy texture, bathed in a silky, sweet, savory sauce. You’ll want to keep eating it for days. This plate disappeared very quickly at our house! This recipe is easy to put together and a great starter recipe for people who are wary of the stuff. In fact, it might turn them into unabashed fans!",
      "location":{
        "latitude":6.254505,
        "longitude":-75.580996
      },
      "pictures":[
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2021/06/BBQ-Tofu-005.jpg"
        },
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2019/05/BBQ-Sauce-006.jpg"
        }
      ]
    },
    {
      "id":"011",
      "name":"Cauliflower Stir Fry",
      "average_cost" : 45335,
      "average_time" : 120,
      "type" : "Pasta",
      "image":"https://www.acouplecooks.com/wp-content/uploads/2020/09/Cauliflower-Stir-Fry-011.jpg",
      "description":"Hands up if you love cauliflower! (All hands raise, over here.) This vegetable has star status among veggies these days: because there are endless creative ways to use it. Make it into tacos, use it as a plant based stand-in buffalo wings, or hide it in alfredo sauce. Here’s our new favorite way to use these tasty florets: in a cauliflower stir fry! Fry it until lightly charred and tender, then add bell peppers and a punchy stir fry sauce. It’s the best way to make eating your veggies irresistible! Try it as a healthy side dish or an easy plant based dinner (with the protein adders below).",
      "location":{
        "latitude":6.254505,
        "longitude":-75.580996
      },
      "pictures":[
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2020/09/Cauliflower-Stir-Fry-004.jpg"
        },
        {
          "id":"xx",
          "url":"https://www.acouplecooks.com/wp-content/uploads/2020/09/Cauliflower-Stir-Fry-003.jpg"
        }
      ]
    }
  ]
}
```

# Screenshots

![alt tag](https://github.com/evernext10/Yape-android-challenge/blob/main/resources/screen_list.png)

![alt tag](https://github.com/evernext10/Yape-android-challenge/blob/main/resources/screen_detail.png)

![alt tag](https://github.com/evernext10/Yape-android-challenge/blob/main/resources/screen_location.png)


# Thanks for reading! 
