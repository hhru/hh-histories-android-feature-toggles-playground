# android-feature-toggles-playground 
Проект для демонстрации работы с feature-флагами.  
  
Это простое многомодульное Android-приложение, состоящее из одного главного экрана с тремя вкладками нижней навигации. Каждая вкладка находится в своём модуле и отображает состояние определённого feature-флага (`true` / `false`).  
  
Также в приложении есть debug-панель, в которой вы сможете поменять состояние того или иного  feature-флага, а затем перезагрузить приложение, чтобы изменения вступили в силу.   
  
## Описание веток  

Практически в каждой ветке присутствуют `TODO`, которые подсвечивают те или иные проблемы. Используйте их для навигации к интересным местам веток. 

### [main](https://github.com/hhru/hh-histories-android-feature-toggles-playground/tree/main)
  
Главная ветка проекта нужна для демонстрации исходной проблемы -   
наличия merge-конфликтов при попытке дополнить enum с feature-флагами (`ExperimentsSet`).   
  
### [avoid_merge_conficts](https://github.com/hhru/hh-histories-android-feature-toggles-playground/tree/avoid_merge_conflicts)  
  
Базовая ветка с решением проблемы merge-конфликтов. Проблема merge-ей исчезла, но повлекла за собой новую задачу: как собрать все feature-флаги, разбросанные по кодовой базе в единый список?   

В этой ветке **не работает** сбор feature-флагов в единый список.   

### [collecting__manual](https://github.com/hhru/hh-histories-android-feature-toggles-playground/tree/collecting__manual)

В ветке показан ручной сбор feature-флагов в единый список, без использования дополнительных библиотек.

### [collecting__hilt](https://github.com/hhru/hh-histories-android-feature-toggles-playground/tree/collecting__hilt)

В ветке демонстрируется использование [Dagger Multibindings](https://dagger.dev/dev-guide/multibindings.html) для сбора флагов в единый `Set`. 

### [collecting__service_loader](https://github.com/hhru/hh-histories-android-feature-toggles-playground/tree/collecting__service_loader)

Для сбора feature-флагов в единый список используется стандартный механизм Java - [ServiceLoader](https://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html). 

Собрав вручную файл [META-INF/services/ru.hh.android.core.experiments.models.Experiment]
(https://github.com/hhru/hh-histories-android-feature-toggles-playground/find/collecting__service_loader), можно использовать метод [ServiceLoader#load](https://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html#load(java.lang.Class)).

### [collecting__metainf_services](https://github.com/hhru/hh-histories-android-feature-toggles-playground/tree/collecting__metainf_services)

Продолжение идеи с [ServiceLoader](https://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html). На этот раз файл META-INF собирается автоматически при помощи библиотеки [metainf-services](https://github.com/kohsuke/metainf-services). Подробнее про неё можно почитать [вот здесь](http://metainf-services.kohsuke.org/). 

### [collecting__classindex](https://github.com/hhru/hh-histories-android-feature-toggles-playground/tree/collecting__classindex)

В ветке демонстрируются два способа работы с библиотекой [ClassIndex](https://github.com/atteo/classindex).

[В этом коммите](https://github.com/hhru/hh-histories-android-feature-toggles-playground/commit/33351fede82f31a359648542c4b366c05293afdb) демонстрируются возможности самой библиотеки [ClassIndex](https://github.com/atteo/classindex): как с её помощью можно легко получить доступ к наследникам определённого интерфейса.

[В следующем коммите](https://github.com/hhru/hh-histories-android-feature-toggles-playground/commit/cc8177d7c153cacbe33d79897ff788d5a237f6ad) снова развивается идея с [ServiceLoader](https://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html). Библиотека [ClassIndex](https://github.com/atteo/classindex) под капотом генерирует файл META-INF, а значит, можно снова использовать [ServiceLoader](https://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html).

### [collecting__classloader_plus_dexfile](https://github.com/hhru/hh-histories-android-feature-toggles-playground/tree/collecting__classloader_plus_dexfile)

Ветка для демонстрации сбора feature-флагов с помощью [ClassLoader](https://docs.oracle.com/javase/7/docs/api/java/lang/ClassLoader.html) и [DexFile](https://developer.android.com/reference/dalvik/system/DexFile). 

### [collecting__byte_code_patching__colonist](https://github.com/hhru/hh-histories-android-feature-toggles-playground/tree/collecting__byte_code_patching__colonist)

В ветке демонстрируются два подхода к использованию библиотеки [Colonist](https://github.com/joomcode/colonist) для патчинга байткода и сбора feature-флагов в единый список. Подробнее про неё можно почитать [вот здесь](https://vc.ru/dev/119802-biblioteka-colonist-poisk-klassov-vo-vremya-kompilyacii-android-prilozheniya).

[В этом коммите](https://github.com/hhru/hh-histories-android-feature-toggles-playground/commit/6534b34014a7f2303a43b014d3de736e183a1d6e) показано использование аннотации `ExperimentSettler` для отметки нужных класов-флагов.

[В этом коммите](https://github.com/hhru/hh-histories-android-feature-toggles-playground/commit/3aa7c8283455c89801ffe12da3538c6f5d6e9e99) показано использование возможностей [Colonist](https://github.com/joomcode/colonist) для получения всех наследников определённого класса.

### [collecting__reflections](https://github.com/hhru/hh-histories-android-feature-toggles-playground/tree/collecting__reflections)

В ветке предпринята неудачная попытка воспользоваться библиотекой [Reflections](https://github.com/ronmamo/reflections) для сбора названий классов-реализаций определённого интерфейса при сборке приложения. В данной ветке **не работает** сбор feature-флагов. 

### [collecting__classgraph](https://github.com/hhru/hh-histories-android-feature-toggles-playground/tree/collecting__classgraph)

В ветке предпринята неудачная попытка воспользоваться библиотекой [Classgraph](https://github.com/classgraph/classgraph) для сбора названий классов, отмеченных определённой аннотацией, при сборке приложения. В данной ветке **не работает** сбор feature-флагов. 