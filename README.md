# Space Station Cargo Transport System

## Overview
You have been asked to design a computer system to help with the transport of material taken to the International Space Station (ISS). The material is stored in containers, which are flown to the ISS and unloaded into three different areas, depending on cargo type. Due to the nature of space flight and the design of the space station, you will need to implement **a stack** and **a queue**.

## Loading and Unloading Process
On Earth, supplies are loaded into pods. Items are placed inside a container, one on top of another, until the container is full. At the destination (the ISS), containers are emptied by removing pods one at a time, starting with the topmost item. Each shuttle carries **two containers** with room for **9 pods each**. 

- **Container 1** is emptied first, followed by **Container 2**.
- **Container 1** always has supplies when traveling to the space station.
- On rare occasions, **Container 2** may be empty, even if transported.

## Cargo Types
There are three types of cargo that are taken to the ISS:
- **Food**
- **Technology**
- **Personal Items**

Each item is marked with a letter identifying the cargo type and a two-digit number. For example:
- `F14` (Food)
- `P22` (Personal)
- `T45` (Technology)

Each pod must have a **unique number**. No duplicates, such as two items labeled `F14`, are allowed.

## Unloading Procedure
Upon reaching the ISS, the shuttle docks and the first container is emptied. The top pod is taken off container 1 and moved through a connecting corridor to the loading bay. The first item in the corridor will be the first item out.

Once a container is fully emptied into the corridor, the supplies can be transferred to the loading bay. The loading bay is divided into three areas:
1. **Bay 1** - Technical
2. **Bay 2** - Personal
3. **Bay 3** - Food

At the beginning of each mission, assume the loading bays are **empty**. After each bay is filled, report the number of items stored in:
- **BAY 1**
- **BAY 2**
- **BAY 3**

## Solution Requirements
Your solution should implement **the Stack** and **Queue Abstract Data Types** from the Java `util` library for this scenario. You will need to read the data from a file and store item IDs as strings.

### Test Data
Test the program with the following two data runs, and include a test run of your own:

#### Data Run 1
- **Pod One:** `F11, F34, P22, T56, F16, T77, P12, F41, T22`
- **Pod Two:** `P19, F39, T92, T36, P36, T75, P15, F48, F88`

#### Data Run 2
- **Pod One:** `T11, T34, P22, F56, T77, P12, F41, T11`
- **Pod Two:** `EMPTY`

If the tests are successful, run the program with the 7 files found in the **missions folder**. Consider these as seven separate missions.

## Code Output
Provide printouts of the stack and queue code used to implement the solution. Additionally, display the state of the stack and queue at various stages:
- Before the pods are loaded
- When the shuttle reaches the space station
- After half the stack has been unloaded
- After the stack has been fully unloaded
- After the loading bay has been used

## Second part of the asssessment use Alternative Files (Non-Java util)
You now need to replace the Java `util` library files with the following custom files:
- `ListNode.java`
- `ListQueue.java`
- `ListStack.java`
- `ArrayQueue.java`
- `ArrayStack.java`
- `Queue.java`
- `Stack.java`
- `UnderFlowException.java`

Replace the Java stack and queue library files with the ones listed above. Ensure the program still runs with minimal modification, and retest to verify functionality.

## Code Output with New Files
Provide printouts of the stack and queue code used to implement the solution. Display the state of the stack and queue at various stages:
- Before the pods are loaded
- Before launch
- When the shuttle reaches the space station
- After the loading bay has been used
