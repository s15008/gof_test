import static java.lang.System.out;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class IteratorPattern {
    public static void main(String...args) {
        PokemonList myPokemon = new PokemonList();
        myPokemon.add(new Pokemon("コラッタ", 5));
        myPokemon.add(new Pokemon("キャタピー", 2));
        myPokemon.add(new Pokemon("ズバット", 3));
        myPokemon.add(new Pokemon("ディグダ", 4));
        out.printf("\t[手持ちのポケモン]\n");
        Iterator it = myPokemon.iterator();
        while(it.hasNext()) {
            // 名前を最大数でフォーマットする
            Pokemon poke = (Pokemon)it.next();
            StringBuilder str = new StringBuilder(poke.getName());
            if (poke.getName().length() < PokemonList.NAME_LEN_MAX) {
                for (int i = 0; i < PokemonList.NAME_LEN_MAX - poke.getName().length(); i++) {
                    str.append("　");
                }
            }
            out.printf("%s\tL%d\n", str, poke.getLevel());
        }

        out.println();

        DQMList myMonsters = new DQMList();
        myMonsters.add(new Monster("スライムナイト", 5, "♂"));
        myMonsters.add(new Monster("スライム", 2, "♂"));
        myMonsters.add(new Monster("おばけキャンドル", 6, "♀"));
        out.printf("\t[なかまのモンスター]\n");
        it = myMonsters.iterator();
        while(it.hasNext()) {
            // 名前を最大数でフォーマットする
            Monster mon = (Monster)it.next();
            StringBuilder str = new StringBuilder(mon.getName()).append(mon.getSex());
            if (mon.getName().length() < DQMList.NAME_LEN_MAX) {
                for (int i = 0; i < DQMList.NAME_LEN_MAX - mon.getName().length(); i++) {
                    str.append("　");
                }
            }
            out.printf("%s\tLv:%d\n", str, mon.getLevel());
        }
    }
}


/**
 * Pokemon Class
 * ポケモンのステータス情報を管理する
 */
class Pokemon {
    private String name;
    private int level;

    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }
}

/**
 * PokemonList Class
 * ポケモンを管理するクラス
 */
class PokemonList extends ConcreateAggregate {
    public static final int NAME_LEN_MAX = 5;
    private int index;

    public PokemonList() {
        this.list = new ArrayList<Pokemon>();
        this.index = 0;
    }

    public void add(Pokemon pokemon) {
        this.list.add(pokemon);
        this.index++;
    }

    public Pokemon getPokemonAt(int index) {
        return (Pokemon)this.list.get(index);
    }
}

/**
 * DQMonstar Class
 * ドラクエのモンスターのステータス情報を管理する
 */
class Monster {
    private String name;
    private int level;
    private String sex;

    public Monster(String name, int level, String sex) {
        this.name = name;
        this.level = level;
        this.sex = sex;
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public String getSex() {
        return this.sex;
    }
}

/**
 * DQMList Class
 * ドラクエのモンスターを管理するクラス
 */
class DQMList extends ConcreateAggregate {
    public static final int NAME_LEN_MAX = 8;
    private int index;

    public DQMList() {
        this.list = new ArrayList<Monster>();
        this.index = 0;
    }

    public void add(Monster monster) {
        this.list.add(monster);
    }

    public Monster getMonsterAt(int index) {
        return (Monster)this.list.get(index);
    }
}

/**
 * ConcreatAggregate Class
 * 集団を実装した具象クラス
 */
class ConcreateAggregate implements Aggregate {
    protected List list;

    public int size() {
        return this.list.size();
    }

    public Object get(int index) {
        return this.list.get(index);
    }

    @Override
    public Iterator iterator() {
        return new ConcreateIterator(this);
    }
}

/**
 * ConcreateIterator Class
 * イテレータを実装した具象クラス
 */
class ConcreateIterator implements Iterator {
    private ConcreateAggregate list;
    private int index;

    public ConcreateIterator(ConcreateAggregate list) {
        this.list = list;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.list.size();
    }

    @Override
    public Object next() {
        return this.list.get(index++);
    }
}

/**
 * Aggregate Interface
 * 集団を操作するインターフェイス
 */
interface Aggregate {
    public Iterator iterator();
}

/**
 * Iterator Interface
 * イテレータを操作するインターフェイス
 */
interface Iterator {
    public boolean hasNext();
    public Object next();
}

