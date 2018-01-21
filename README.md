# Abstract Adapter

![Alt Text](https://media.giphy.com/media/xULW8rMc4sapirgLFC/giphy.gif)
[![](https://jitpack.io/v/Vennich/Adstract_Adapter.svg)](https://jitpack.io/#Vennich/Adstract_Adapter)
#### What it is?
***
This is a my try to make life with Android **RecyclerView.Adapter** more simple. 
This project is embodiment of the idea how to an adapter make modular - easier addition and removal of different types of ViewHolder. 
#### Installation
***
Add to add it in your root **build.gradle** at the end of repositories: 
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency  in your app **gradle** file:
```gradle
dependencies {
	        compile 'com.github.Vennich:Adstract_Adapter:0.1.10'
	}
```
#### Usage
***
To make a modular adapter you should create ViewHolders classes and Factories which create them and then just add to adapter. 
##### Step 1. Create ViewHolder
You should create a class which extends *AbstractViewHolder* and override *setData()*
```kotlin
class YourViewHolderName(itemView: View) : AbstractViewHolder(itemView) {
    override fun setData(position: Int, data: ViewHolderData) {
        //here we will convert ViewHolderData to our data type
    }
}
```
##### Step 2. Create Factory
Create your factory class which implements *HolderFactory* and implements methods:
```kotlin
class YourViewHolderFactoryName : HolderFactory {
    override fun getLayoutId() : Int {
        //return ViewHolder layout id
        return R.layout.your_view_holder_id
    }
    
    //create ViewHolder
    override fun createViewHolder(parent: ViewGroup): AbstractViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutId(), parent, false)
        return YourViewHolderName(view)
    }
    
}
```
Also I advise to create methods to convert some values to class ViewHolderData and static method to decode it dack, to use in your's ViewHolder class:
```kotlin
    //if you don't need access by unique key, you can set empty String 
    fun createHolderData(forExampleText: String, uniqueKey : String): ViewHolderData {
    return ViewHolderData(getLayoutId(), forExampleText, uniqueKey)
}
companion object {
        //decode to your data class
        //in our example it was String
        fun convertToData(viewHolderData: ViewHolderData) : String {
            return viewHolderData.data as String
        }
        
    }
```
##### Step 2.1 If you create converter methods in your Factory
In your created ViewHolder class add decode line 
```kotlin
   override fun setData(position: Int, data: ViewHolderData) {
        //data can be a private variable or as you with
        val data: String = YourViewHolderFactoryName.convertToData(data)
    }
```
##### Step 3
You have left to build Adapter class and work with it. 
Create a field in class
```kotlin
private lateinit var adapter : AbstractAdapter
private lateinit var yourFactory : YourViewHolderFactoryName
```
Create *yourFactory* and init Adapter
For example in activity's *onCreate()* method:
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    ...
    
    yourFactory = YourViewHolderFactoryName()
    
    //you can set initial capacity on use default constructor
    adapter = AbstractAdapter.Builder(1)
                .setFactory(yourFactory)
                .build()
                
    //if you need, you can here set up adapter to RecyclerView
    recycler.adapter = adapter
}
    
```
If you create converter in your factory you can set up data when you got it like this:
```kotlin
fun onGetDataItem(item: String) {
    adapter.add(yourFactory.createHolderData(item, "")
}
```
And that all.
You can use as simple adapter and add a lot of different ViewHolder types as you need.
### Listening ViewHolder events
If you need to listen your ViewHolder events, I show you with simple event:
When you click at item - it will be removed. 
Write a simple interface **ItemRemoveListener**
```kotlin
interface RemoveItemListener {
    fun onRemove(position: Int)
    
}
```
Add listener to **YourViewHolderFactoryName** and **YourViewHolderName**
In ViewHolder init OnClickListener and handle *onClick()* event:
```kotlin
class YourViewHolderName(itemView: View,
                         private val onRemoveItemListener: RemoveItemListener)
    : AbstractViewHolder(itemView), View.OnClickListener {
    ...
    
    init {
        itemView.setOnClickListener(this)
    }
    
    override fun onClick(item: View) {
        if (adapterPosition == -1) {
            //animation running and we are trying don't need to remove already removing view
            //it will cause of Exception or wrong behavior
        } else {
            onRemoveItemListener.onRemove(adapterPosition)
        }
    }
    
}
```
in factory add to constructor and provide it to your ViewHolder:
```kotlin
class YourViewHolderFactoryName(private val onRemoveItemListener: RemoveItemListener) : HolderFactory {
    ...
    
    override fun createViewHolder(parent: ViewGroup): AbstractViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return YourViewHolderName(view, onRemoveItemListener)
    }
}
```
And now implement it on your View's class and provide to Factory listener:
```kotlin
 class MainActivity : AppCompatActivity(), RemoveItemListener {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        ...
        
        yourFactory = YourViewHolderFactoryName(this)
    }
    
    override fun onRemove(position: Int) {
        adapter.removeItem(position)
    }
 
 }
```
### Other 
***
It my first library and I will be glad to see your comments and advices. 
License
----
MIT
