package account

import "sync"

// Account stores the bank account's status.
type Account struct {
	sync.RWMutex
	balance int64
	active  bool
}

// Open an account with initial deposit.
func Open(initialDeposit int64) *Account {
	if initialDeposit < 0 {
		return nil
	}
	return &Account{
		balance: initialDeposit,
		active:  true,
	}
}

// Close an account.
func (acc *Account) Close() (payout int64, ok bool) {
	acc.Lock()
	defer acc.Unlock()

	defer func() {
		acc.balance = 0
		acc.active = false
	}()
	return acc.balance, acc.active
}

// Balance returns the account balance.
func (acc *Account) Balance() (balance int64, ok bool) {
	acc.RLock()
	defer acc.RUnlock()
	return acc.balance, acc.active
}

// Deposit will try to deposit/withdraw balance from account.
func (acc *Account) Deposit(amount int64) (newBalance int64, ok bool) {
	acc.Lock()
	defer acc.Unlock()

	newBalance = acc.balance + amount
	if !acc.active || newBalance < 0 {
		return acc.balance, false
	}
	acc.balance = newBalance
	return acc.balance, true
}
