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
    constructor() {
        super();
        this.state = {
            ds: new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2}),
            active: 'all',
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
                        centerElement="Material Todo List"
                    />

                    <TextInput
                        style={{height: 56, borderColor: 'grey', borderWidth: 0}}
                        onChangeText={(text) => this.setState({edit: text})}
                        value={this.state.edit}
                        onSubmitEditing={(event) => this.addNewItem(event.nativeEvent.text)}
                    />

                    <ListView
                        style={{ flex: 1 }}
                        dataSource={this.state.ds.cloneWithRows(this.state.items)}
                        renderRow={(rowData) => {
                            // todo: filter items by active
                            let color = rowData.done ? COLOR.grey300 : '#ffffff00';
                            return (<TouchableNativeFeedback
                                background={TouchableNativeFeedback.SelectableBackground()}>
                                <View style={{alignSelf: "stretch", height: 56, backgroundColor: color}}>
                                    <Text style={{textAlign: 'center', margin: 30}}>{rowData.title}</Text>
                                </View>
                            </TouchableNativeFeedback>);
                        }}
                    />

                    <BottomNavigation active={this.state.active} hidden={false}>
                        <BottomNavigation.Action
                            key="all"
                            icon="home"
                            label="all"
                            onPress={() => this.setState({active: 'all'})}/>
                        <BottomNavigation.Action
                            key="pending"
                            icon="compare-arrows"
                            label="pending"
                            onPress={() => this.setState({active: 'pending'})}/>
                        <BottomNavigation.Action
                            key="done"
                            icon="archive"
                            label="done"
                            onPress={() => this.setState({active: 'done'})}/>
                    </BottomNavigation>
                </View>
            </ThemeProvider>
        )
    }

    addNewItem(str) {
        let i = this.state.items;
        i.unshift({text: str, done: false});
        this.setState({edit: '', items: i});
    }
}

module.exports = MainActivity;
