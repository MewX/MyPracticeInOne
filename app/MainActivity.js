/**
 * Created by MewX on 4/22/2017.
 */

import * as React from "react";
import {ListView, StatusBar, Text, TextInput, TouchableNativeFeedback, View} from "react-native";
import {Toolbar, BottomNavigation, ThemeProvider} from 'react-native-material-ui';
import * as COLOR from "react-native-material-ui/src/styles/colors";

const uiTheme = {
    palette: {
        primaryColor: COLOR.green500,
        accentColor: COLOR.pink500,
    },
    toolbar: {
        container: {
            height: 56,
        },
    },
};

class MainActivity extends React.Component {
    static ACTIVE_ALL = 'ALL';
    static ACTIVE_PENDING = 'PENDING';
    static ACTIVE_DONE = 'DONE';

    constructor() {
        super();
        this.state = {
            ds: new ListView.DataSource({rowHasChanged: (ori, n) => ori !== n}),
            active: MainActivity.ACTIVE_ALL,
            edit: '',
            items: [
                {text: 'Done sample', done: true},
                {text: 'Pending sample', done: false}
            ]
        };

        // bindings
        // this.addNewItem = this.addNewItem.bind(this);
    }



    componentWillMount() {
    }

    render() {
        return (
            <ThemeProvider uiTheme={uiTheme}>
                <View style={{flex: 1}}>
                    {/*Interesting ways to make translucent*/}
                    <StatusBar backgroundColor="rgba(0, 0, 0, 0.2)" translucent />
                    <View style={{ backgroundColor: COLOR.green500, height: 24 }} />
                    <Toolbar
                        leftElement="menu"
                        centerElement="RN Material Todo List"
                    />

                    <TextInput
                        style={{paddingLeft: 12, paddingRight: 12, fontSize: 16, borderColor: 'grey', borderWidth: 0}}
                        onChangeText={(text) => this.setState({edit: text})}
                        placeholder="What to do?"
                        value={this.state.edit}
                        onSubmitEditing={(event) => this.addNewItem(event.nativeEvent.text)}
                    />

                    <ListView
                        style={{ flex: 1 }}
                        dataSource={this.state.ds.cloneWithRows(this.state.items.filter((it) => {
                            switch (this.state.active) {
                                case MainActivity.ACTIVE_DONE:
                                    return it.done;
                                case MainActivity.ACTIVE_PENDING:
                                    return !it.done;
                                default:
                                    return true;
                            }
                        }))}
                        renderRow={(rowData) => {
                            // todo: filter items by active
                            let color = rowData.done ? COLOR.grey300 : '#ffffff00';
                            console.log(rowData.text);
                            return (<TouchableNativeFeedback
                                background={TouchableNativeFeedback.SelectableBackground()}>
                                <View style={{alignSelf: "stretch", backgroundColor: color}}>
                                    <Text style={{padding: 12, textAlign: 'left', justifyContent: 'center', fontSize: 16}}>{rowData.text}</Text>
                                </View>
                            </TouchableNativeFeedback>);
                        }}
                    />

                    <BottomNavigation active={this.state.active} hidden={false}>
                        <BottomNavigation.Action
                            key={MainActivity.ACTIVE_ALL}
                            icon="home"
                            label="All"
                            onPress={() => this.setState({active: MainActivity.ACTIVE_ALL})}/>
                        <BottomNavigation.Action
                            key={MainActivity.ACTIVE_PENDING}
                            icon="compare-arrows"
                            label="Pending"
                            onPress={() => this.setState({active: MainActivity.ACTIVE_PENDING})}/>
                        <BottomNavigation.Action
                            key={MainActivity.ACTIVE_DONE}
                            icon="archive"
                            label="Done"
                            onPress={() => this.setState({active: MainActivity.ACTIVE_DONE})}/>
                    </BottomNavigation>
                </View>
            </ThemeProvider>
        )
    }

    addNewItem(str) {
        // pre process, and ignore empty string
        str = str.trim();
        this.setState({edit: ''});
        if (!str || !str.length) return;

        let i = this.state.items;
        i.unshift({text: str, done: false});
        this.setState({items: i});
    }
}

module.exports = MainActivity;
